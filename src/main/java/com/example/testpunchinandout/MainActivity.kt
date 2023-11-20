package com.example.testpunchinandout
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testpunchinandout.ui.theme.TestPunchInAndOutTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private var isTablet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isTablet = isTabletDevice()  // check if device is a phone or a tablet

        setContent {
            TestPunchInAndOutTheme {
                navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    if(!isTablet){ // when using a phone
                        PhoneNavGraph(navController = navController)
                    } else {
                        TabletNavGraph(navController = navController)
                    }
                }
            }
        }
    }

    private fun isTabletDevice(): Boolean {   // Based on the screen size of the device, it is determined whether it is a tablet or a phone.
        val displayMetrics = resources.displayMetrics
        val width = displayMetrics.widthPixels / displayMetrics.xdpi
        val height = displayMetrics.heightPixels / displayMetrics.ydpi
        val screenSize = sqrt(width * width + height * height.toDouble())
        return screenSize >= 7
    }

}


