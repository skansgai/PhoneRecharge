package huawei.com;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("huawei.com", appContext.getPackageName());
    }
    @Test
    public void testMainActivity(){
        //方法第一个参数的意思是，我们预期的值；第二个参数的意思是，实际结果值。很显然它会报错。
        assertNotEquals(0,1);
        //这个就不会报错
        assertEquals(1,1);
    }
}
