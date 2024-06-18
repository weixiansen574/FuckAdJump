package top.weixiansen574.fuckadjump;

import android.net.Uri;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XPosedMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedHelpers.findAndHookMethod(Uri.class,"parse",String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                String arg = (String) param.args[0];

                if (arg == null){
                    return;
                }
                if (arg.startsWith("http")){
                    return;
                }
                if (arg.startsWith("openapp.jdmobile")){
                    XposedBridge.log("已拦截跳转京东！");
                    param.args[0]= "";
                }
                if (arg.startsWith("tbopen")){
                    XposedBridge.log("已拦截跳转淘宝！");
                    param.args[0]= "";
                }
            }
        });
    }
}
