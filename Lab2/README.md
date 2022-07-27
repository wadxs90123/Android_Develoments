Practiced "Data Binding" and "Life Data"
To Enabled Data Binding in Gradle(Module),

 defaultConfig {
        applicationId "com.example.lab2"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"
        /*******************/
        dataBinding{        //Important!!!
            enabled true    //Important!!!
        }                   //Important!!!
        /*******************/
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    
Do like above.

Data Binding -> Used to Bind the datas in XML files(ViewMode -> XML).
Life Data -> Dynamically update datas(ViewModel).
