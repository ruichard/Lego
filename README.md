# Lego
Lego is a component-based architecture used for a large-scale, matrix-style Android app, and its design philosophy is derived from microservices architecture. It allows a large application to be separated into smaller independent components, with each component having its own realm of responsibility.
It provides the ability to route communication between Gradle projects, as well as the ability of component definition, version control, Maven publishing, AAR/JAR and switching source code, and free composition of components.

Lego consists of two parts:
* Lego Router ：The function level routing capability of Lego. Unlike normal page router, Lego Router allows the Uri and parameters to be navigated to any execution of a public JVM language (Java/Kotlin) function . It allows for more flexible communication between Gradle Projects without code calls.
* Lego Tools link ：Provides component context definition, versioning, Maven publishing, AAR/JAR and source switching capabilities, including 4 Gradle Plugins:
    + lego：
        - Provides the ability to define components globally, and automatically enables plugins such as lego-context and lego-root based on the global definition
    + lego-context：
        - Provide Task, automatically generate the intermediate code such as mirror function, and package the intermediate code into context.jar, release to Maven according to the version.
        - Provides tasks to compile business codes into AAR (including code, resources, and built-in SDK) based on flavor and version. publish them to Maven.
        - Automatically adds dependencies on other context.jars to subprojects where components reside
    + lego-root：
        - Provides the ability to picking components, picking components to be packaged into APK based on flavor and version.
        - Provide component source code and AAR switching ability.
    + lego-test:
        - Provide unit testing environment for projects

## Quick start
### 1. Project creation and component declaration：
&ensp;&ensp;(1) Create or use one or more existing Android Library Modules as "component projects" (demo_component_detail, DEMO_component_home, etc. in Demo code) for developing real business logic.

&ensp;&ensp;(2) Set Lego initialization parameters such as the Lego version in the build.gradle or gradle.properties file of the gradle root project. and enable the Lego plugin in the gradle root project:
```groovy
ext {
    lego_kapt_version = "com.lego:kapt:1.9.1.1-K1_5"   
    lego_router_version = "com.lego:router:1.9.1.1-K1_5"   
    lego_plugins_version = "com.lego:plugins:1.9.1.1-AGBT4-K1_5"  
} 

apply plugin: 'lego' // enable the Lego plugin
```

&ensp;&ensp;(3) In the build.gradle file of the outermost project ,or lego-*.gradle file in its sibling directory, configure the component information:
```groovy
lego {
    component { // The first component
        uri "app://com.myapp.home"  //   The Uri of component
        dependencies {    // The component needs to depend on other components
            uri ("app://com.myapp.detail" ) { 
                version "0.1.1"  // Versions that depend on other components
            }
            uri( … ) 
        }
        source {    // Define the default source.
            project (":demo_component_home") 
        }
    }
    component { … }  // Proceed to configure the second component
} 
```

### 2. Let components communicating to each other.：
&ensp;&ensp;(1). In the interface provider project, defined  routing path by annotations, as a communication interface that a component exposes to other components:

&ensp;&ensp;&ensp;&ensp;Declare function route with the RFunction annotation:
```kotlin
@RFunction(path = "account/user") 
fun getUser(id : Int, name : String) : User? { 
    …
}
```

&ensp;&ensp;&ensp;&ensp;Declare page route with the RPage annotation:
```kotlin
@RPage(path = "page/main") 
class HomeActivity : AppCompatActivity() {
    … 
}
```
&ensp;&ensp;(2). Run "publishLegoXxxRContextLib" task in the interface provider project, release the component context to the cloud or local maven repository.

&ensp;&ensp;(3). Run "publishLegoXxxRComponent" task in the interface provider project, release the component aar to the cloud or local maven repository.

&ensp;&ensp;(4). In the interface caller project, there are two ways to call the interface provided by the above interface provider:

&ensp;&ensp;&ensp;&ensp;Via Kotlin DSL:
```kotlin
navigate {
    uri = "app://com.myapp.detail/account/user"  // The request uri
    query { // Parameters of the request
        "id" with 400
        "name" with "CuiVincent" 
    }
    result<User?> { user -> 
    // The received data type is specified by generics, and multiple asynchronous returns can be received as multiple results
        …
    }
} 
```

&ensp;&ensp;&ensp;&ensp;Via automatically generated image functions:
```kotlin
DetailContext.Account.user(400, "CuiVincent" ) { user ->
    … // The parameter type and return value type of the automatically generated image function are clear, which is more binding than the DSL
}
```

### 3. Picking components to package
&ensp;&ensp;(1). Create or use an existing Android Application Project as a "shell project" (demo_root_app in demo code) to assemble and compile components to Apk.

&ensp;&ensp;(2).  In the build.gradle file of the shell project or lego-*.gradle file in its sibling directory, specify which components the shell project will eventually bring in and how they will be packaged into the final build product:
```groovy
lego {	
    packing {
        projectMode { // projectMode，Introduce components through source code project
            uri ("app://com.myapp.home")
            uri ("app://com.myapp.*") // Support to match any character through *
        }
        mavenMode { // mavenMode，Introduce components through AAR on maven
            uri ("app://com.myapp.detail") {
                version "0.2.0" 
            }
        }
    }
} 
```
## Test
* Through the lego-test plugin, add all the context.jar dependencies of picked components to androidTest Variant of the current project, which is easy to write test cases.
```kotlin
@RunWith(AndroidJUnit4::class)
class RouterTestCase {
    @Before
    fun init() {
        Lego.init()
    } // Initialize Lego
    @Test
    fun usePerview() {
        PerviewContext.preViewVideo(path) { success ->
            log("preViewVideo success:${success}")
        } // Test case
    }
    … // Continue to write test cases
}

```
