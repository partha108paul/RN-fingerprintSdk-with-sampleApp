package com.reactnativefingerprint

import android.content.Context
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

import com.prism.device.BureauInstanceProvider
import com.prism.device.models.ErrorResponse
import com.prism.device.models.SubmitResponse
import com.prism.device.tools.DataCallback
//import com.prism.device.tools.ENV_PRODUCTION
//import com.prism.device.tools.ENV_SANDBOX
import com.prism.device.tools.Environment.Companion.ENV_PRODUCTION
import kotlin.reflect.typeOf

class FingerprintModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  //  private lateinit var lateinitcontext: ReactApplicationContext? = null
  private var context: Context = reactApplicationContext.applicationContext

  //  FingerprintModule(context:ReactApplicationContext ) {
//    super(context);
//    this.context = context.applicationContext
//  }


  override fun getName(): String {
    return "Fingerprint"
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Int, b: Int, promise: Promise) {
    promise.resolve(a * b)
  }

  @ReactMethod
  fun processBureauTest(
    clientId: String,
    sessionId: String,
    userId: String = "",
    flow: String = "",
    promise: Promise
  ) {
    Log.i("FINGERPRINT_REACT_SIREN", "$clientId $sessionId")
    val bureauAPI = BureauInstanceProvider.getBureauApiInstance(
      context, clientId,
      ENV_PRODUCTION
    )
//    Log.i("FINGERPRINT_REACT_SIREN", bureauAPI::class!!.java.typeName!!)

    bureauAPI.setSessionId(sessionId)
    bureauAPI.setUserId(userId)
    bureauAPI.setFlow(flow)

    bureauAPI.submit(object :
      DataCallback {
      override fun onError(errorMessage: ErrorResponse) {
        //promise.resolve("ERROR")
        Log.i("FINGERPRINT_REACT_SIREN", "Fail Hua $errorMessage")
      }

      override fun onResult(message: SubmitResponse) {
        //promise.resolve("SUCCESS")
        Log.i("FINGERPRINT_REACT_SIREN", "Success Hua")
      }
    })
  }

}
