package pt.ipg.sapatilhas

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

//import android.provider.ContactsContract.Intents.Insert

import org.junit.Assert.*
import org.junit.Before
//import org.junit.runner.manipulation.Ordering.Context
import pt.ipg.sapatilhas.Bd.SapatilhasOpenHelper




@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {
    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
       // getAppContext().deleteDatabase(SapatilhasOpenHelper.NOME_BASE_DE_DADOS)
    }
    @Test
    fun consegueAbrirBaseDados() {
        val openHelper=SapatilhasOpenHelper(getAppContext())
        val bd=openHelper.readableDatabase
        assert(bd.isOpen)
        // Context of the app under test.
        //val appContext = getAppContext()
        //assertEquals("pt.ipg.sapatilhas", appContext.packageName)
    }
    @Test
    fun consegueInserirMarcas(){
        val bd = getWritableDataBase()
        val marca=Marca("Nike","Lisboa")
        insertMarca(bd,marca)
    }

    @Test
    fun consegueInserirSapatilhas(){
        val bd = getWritableDataBase()

        val marca=Marca("Nike","Lisboa")

        insertMarca(bd, marca )

        val sapatilha1=Sapatilha("Air force 1","Vermelho",44,"T231", marca)
        insertSapatilha(bd,sapatilha1)


        val sapatilha2=Sapatilha("Air force 4","Vermelho",44,"T231",marca)
        insertSapatilha(bd,sapatilha2)



    }

    private fun insertMarca(bd: SQLiteDatabase,marca:Marca) {

        marca.id=TabelaMarca(bd).insere(marca.toContentValues())
        assertNotEquals(-1,marca.id)
    }
    private fun insertSapatilha(bd: SQLiteDatabase,sapatilha: Sapatilha) {
        sapatilha.id=TabelaSapatilha(bd).insere(sapatilha.toContentValues())
        assertNotEquals(-1,sapatilha.id)


    }

    private fun getWritableDataBase(): SQLiteDatabase {
        val openHelper = SapatilhasOpenHelper(getAppContext())
        return openHelper.writableDatabase

    }

    @Test
    fun consegueLerMarcas(){
        val bd= getWritableDataBase()

        val marcaPuma=Marca("Puma","Londres")
        insertMarca(bd,marcaPuma)

        val marcaFila=Marca("Puma","Tokio")
        insertMarca(bd,marcaFila)

        val tabelaMarca = TabelaMarca(bd)
        val cursor: Cursor= tabelaMarca.consulta(
            TabelaMarca.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(marcaPuma.id.toString()),
            null,
            null,
            null
            )
        assert(cursor.moveToNext())
        val marcaBD=Marca.fromCursor(cursor)

        assertEquals(marcaPuma,marcaBD)

        val cursorTodasMarcas=tabelaMarca.consulta(
            TabelaMarca.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaMarca.Campo_Nome

        )

        assert(cursorTodasMarcas.count>1)
    }

    @Test
    fun  consegueLerSapatilha(){
        val bd= getWritableDataBase()

        val marcaPuma=Marca("Puma","Londres")
        insertMarca(bd,marcaPuma)

        val sapatilha1=Sapatilha("Air Jordan 1","Branca",44,"AE456",marcaPuma)
        insertSapatilha(bd,sapatilha1)

        val sapatilha2=Sapatilha("Air Jordan 4","Azul",44,"AE453",marcaPuma)
        insertSapatilha(bd,sapatilha2)

        val tabelaSapatilha = TabelaSapatilha(bd)
        val cursor: Cursor= tabelaSapatilha.consulta(
            TabelaSapatilha.CAMPOS,
            "${TabelaSapatilha.CAMPO_ID}=?",
            arrayOf(sapatilha1.id.toString()),
            null,
            null,
            null
        )
        assert(cursor.moveToNext())
        val sapatilhaBD=Sapatilha.fromCursor(cursor)

        assertEquals(sapatilha1,sapatilhaBD)

        val cursorTodasSapatilhas=tabelaSapatilha.consulta(
            TabelaSapatilha.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaSapatilha.CAMPO_MODELO)
        assert(cursorTodasSapatilhas.count>1)

    }
    @Test
    fun consegueAlterarMarca(){
        val bd = getWritableDataBase()
        val marca=Marca("Ardidas","Lisboa")
        insertMarca(bd,marca)

        marca.nome="Adidas"
        marca.sede="Porto"

        val registosAlterados=TabelaMarca(bd).altera(
            marca.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(marca.id.toString()),)

        assertEquals(1,registosAlterados)
    }


    @Test
    fun consegueAlterarSapatilha(){
        val bd = getWritableDataBase()

        val marca3=Marca("Puma","Amesterdao")
        insertMarca(bd,marca3)

        val marca2=Marca("Nike","Madrid")
        insertMarca(bd,marca2)

        val sapatilha1=Sapatilha("Air Jordan 1","Branca",44,"AE456",marca3)
        insertSapatilha(bd,sapatilha1)



        sapatilha1.Modelo="Air Jordan 5"
        sapatilha1.Cor="Amarelo e Branco"
        sapatilha1.Tamanho=45
        sapatilha1.SerialNumber="AE496"
        sapatilha1.marca=marca2

        val registosAlterados=TabelaSapatilha(bd).altera(
            sapatilha1.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(sapatilha1.id.toString()),
        )
        //verificar em casa se todos os campos estão como uposto

        assertEquals(1,registosAlterados)
    }

    @Test
    fun  consegueApagarMarcas(){
        val bd = getWritableDataBase()
        val marca=Marca("Ardidas","Lisboa")
        insertMarca(bd,marca)


        val registosEliminados=TabelaMarca(bd).elimine(
            "${BaseColumns._ID}=?",
            arrayOf(marca.id.toString()),)

        assertEquals(1,registosEliminados)
    }


    @Test
    fun consegueApagarSapatilha(){
        val bd = getWritableDataBase()

        val marca3=Marca("Puma","Amesterdao")
        insertMarca(bd,marca3)


        val sapatilha1=Sapatilha("Air Jordan 1","Vermelho",44,"AE756",marca3)
        insertSapatilha(bd,sapatilha1)


        val registosEliminados=TabelaSapatilha(bd).elimine(
            "${BaseColumns._ID}=?",
            arrayOf(sapatilha1.id.toString()),
        )

        assertEquals(1,registosEliminados)
    }

}