package pt.ipg.sapatilhas

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith


import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.manipulation.Ordering.Context
import pt.ipg.sapatilhas.Bd.SapatilhasOpenHelper

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {
    private fun getAppContext(): android.content.Context =
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


}