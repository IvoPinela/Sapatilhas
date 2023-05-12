package pt.ipg.sapatilhas

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.provider.ContactsContract.Intents.Insert

import org.junit.Assert.*
import org.junit.Before
//import org.junit.runner.manipulation.Ordering.Context
import pt.ipg.sapatilhas.Bd.SapatilhasOpenHelper

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {
    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(SapatilhasOpenHelper.NOME_BASE_DE_DADOS)
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
        TabelaMarca(bd).insere(marca.toContentValues())
        assertNotEquals(-1,marca.id)
    }

    @Test
    fun consegueInserirSapatilhas(){
        val bd = getWritableDataBase()
        val marca=Marca("Nike","Lisboa")
        InsertMarca(bd, marca )
        val sapatilha1=Sapatilha("Air force 1","Vermelho",44,"T231",marca.id)
        InsertSapatilha(bd,sapatilha1)

        val sapatilha2=Sapatilha("Air force 4","Vermelho",44,"T231",marca.id)
        InsertSapatilha(bd,sapatilha2)

    }

    private fun InsertMarca(bd: SQLiteDatabase,marca:Marca) {

        marca.id=TabelaMarca(bd).insere(marca.toContentValues())
        assertNotEquals(-1,marca.id)
    }
    private fun InsertSapatilha(bd: SQLiteDatabase,sapatilha: Sapatilha) {
        TabelaSapatilha(bd).insere(sapatilha.toContentValues())
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
        InsertMarca(bd,marcaPuma)

        val marcaFila=Marca("Puma","Tokio")
        InsertMarca(bd,marcaFila)

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


}