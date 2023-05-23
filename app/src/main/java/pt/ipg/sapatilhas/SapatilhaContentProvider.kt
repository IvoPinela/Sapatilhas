package pt.ipg.sapatilhas

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import pt.ipg.sapatilhas.Bd.SapatilhasOpenHelper

class SapatilhaContentProvider:ContentProvider(){
    private var bdopenHelper: SapatilhasOpenHelper?=null

    override fun onCreate(): Boolean {
        bdopenHelper= SapatilhasOpenHelper(context)
        return true
        
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val bd=bdopenHelper!!.readableDatabase

        val endereco=uriMatcher().match(uri)

        val tabela=when(endereco) {
            URI_MARCAS,URI_MARCAS_ID -> TabelaMarca(bd)
            URI_SAPATILHAS,URI_SAPATILHAS_ID -> TabelaSapatilha(bd)
            else -> null
        }
        val id=uri.lastPathSegment
        val (selecao,argsSel) =when(endereco) {
            URI_SAPATILHAS_ID,URI_MARCAS_ID -> Pair("${ BaseColumns._ID}=?", arrayOf(id))
            else -> Pair(selection,selectionArgs)
        }
        //content://pt,ipg.livros/livros
        //selection="Titulo LIKE '?%'"
        //selectionArgs={'a'}

        //content://pt,ipg.livros/livros/5
        //selection="_id=?"
        //selectionArgs={'5'}

        return tabela?.consulta(
            projection as Array<String>,
            selecao,
            argsSel as Array<String>?,
            null,
            null,
            sortOrder)
        }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, projection: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, projection: String?, selection: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri,
                        projection: ContentValues?,
                        selection: String?,
                        selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        private const val AUTORIDADE="pt.ipg.sapatilhas"
        const val MARCAS="marcas"
        const val SAPATILHAS="sapatilhas"

        private const val URI_MARCAS=100
        private const val URI_MARCAS_ID=101
        private const val URI_SAPATILHAS=200
        private const val URI_SAPATILHAS_ID=201
        fun  uriMatcher()=UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE,MARCAS,URI_MARCAS)
            addURI(AUTORIDADE,"$MARCAS/#",URI_MARCAS_ID)
            addURI(AUTORIDADE,SAPATILHAS,URI_SAPATILHAS)
            addURI(AUTORIDADE,"$SAPATILHAS/#",URI_SAPATILHAS_ID)
            /*
            content://pt.ipg.sapatilhas/marcas->100
            content://pt.ipg.sapatilhas/marcas/5->100
            content://pt.ipg.sapatilhas/sapatilhas->100
             */
        }

    }

}