package pt.ipg.sapatilhas

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import pt.ipg.sapatilhas.Bd.SapatilhasOpenHelper

class SapatilhaContentProvider:ContentProvider(){
    private var bdopenHelper: SapatilhasOpenHelper?=null

    override fun onCreate(): Boolean {
        bdopenHelper= SapatilhasOpenHelper(context)
        return true
        
    }

    override fun query(
        uri: Uri,
        values: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    companion object{
        private const val AUTORIDADE="pt.ipg.sapatilhas"
        const val MARCAS="marcas"
        const val SAPATILHAS="sapatilhas"

        private const val URI_MARCAS=100
        private const val URI_SAPATILHAS=200
        fun  uriMatcher()=UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE,MARCAS,URI_MARCAS)
            addURI(AUTORIDADE,SAPATILHAS,URI_SAPATILHAS)
            /*
            content://pt.ipg.sapatilhas/marcas
            
             */
        }
        
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, values: String?, selection: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
    

}