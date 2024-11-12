package skosana.avianaapp1.Presentation.Common

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable


fun ToastMsg(context: Context,message: String ) = Toast.makeText(context,message ,Toast.LENGTH_LONG)
