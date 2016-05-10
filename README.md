# android-rxmvvmdi
Project for introducing Android developers to how one can combine Rx, MVVM, DI, data-binding, and unit testing concepts. Each activity introduces a certain set of concepts which are documented in the activity class itself.

## Setup
### JDK8
This project uses retrolambda, so you need to make sure to have [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed and the `JAVA8_HOME` environment variable set to point to it.

### Android Studio & Gradle
Android Studio >= v2.1 recommended. May work with earlier versions, but you might run into issues with the combination of data-binding libraries, retrolambda, and dagger that uses android-apt. The project depends on android build tools v2.0.0, it does not build with v2.1.0.

## Exercise 1
Run the `MainActivityViewModelTests`. Note that one of the tests failed.
Modify the test and `MainViewModel` so that the test successfully verifies that the app has tried to navigate to `StaticBindingExampleActivity.class`.

Problems? see [Hints](#hints)

## Exercise 2
Open `activity_static_binding_example.xml` and `StaticBindingExampleViewModel`. We want the `android:textColor` field of the last `TextView` to change according to which coffee capsule flavor is being displayed in the `TextView`. The coffee capsule is bound to the view from the view model using data-binding, and we would also like the `textColor` to change using data-binding.

If the capsule is of type `CoffeeType.CAPRICCIO`, the text color should be `0x3EE400`. If it is `CoffeeType.LIVANTO`, the text color should be `0xFF9900`. Otherwise, leave it black (`0x000000`)

You may find the Android [Data Binding Guide](http://developer.android.com/tools/data-binding/guide.html) useful.

Problems? see [Hints](#hints)

## Exercise 3
### Step 1
Run the app and open `RxBindingExampleActivity`. Note the crash as it opens. Fix the crash. You may find the [observeOn](http://reactivex.io/documentation/operators/observeon.html) documentation helpful.
### Step 2
Run the activity again. Click the calculate button and note that nothing happens. Bind the button to the `RxBindingExampleViewModel.calculate()` function using data-binding.
### Step 3
Run the activity again. Click the calculate button and notice that the timer freezes for a second. Fix the code so that the UI thread does not freeze when `calculate()` is called (you still need to execute the `Thread.sleep`, don't remove that ;) ).

Problems? see [Hints](#hints)

## Exercise 4
### Step 1
Open `activity_rx_property_example.xml` and its view model, `RxPropertyExampleViewModel`. Create a new `RxProperty` that tracks the maximum CPU usage so far and bind it to the `id/max_cpu_text` `TextView`'s text field.
### Step 2
Run the activity and observe logcat. Look for lines that look something like `User 4%, System 10%, IOW 0%, IRQ 0%`. Are they getting outputted twice every time the CPU usage is updated? If so, fix it so that it only happens once for every update. You may find [share](http://reactivex.io/RxJava/javadoc/rx/Observable.html#share()) documentation helpful.
### Step 3
Use two-way binding to update the `android:textColor` property of the `id/two_way_text` `EditText`, so that when the length of text in the `EditText` (or its bound vm.customInput property) is greater than 5, it switches color to `@color/red`. Otherwise, the text color should be `@color/black`. You can do this purely by using the data-binding [expression language](http://developer.android.com/tools/data-binding/guide.html#expression_language) in the XML file.

Problems? see [Hints](#hints)

## Exercise 5
Have a look at `IBackendService`. This service will be configured by retrofit to connect to a test backend to fetch data. The data consists of posts by users. You can use `getPosts()` to get all posts and then `getUser(int userId)` to get information about a certain user. The aim of this exercise is to create a new activity that finds the post with the longest body, and then displays the body text, its length, and the name of the user who created it.

1. Create a new activity.
2. Modify `ActivityComponent` and `ActivityModule` so you can inject dependencies into that activity.
3. Create a new button in `MainActivity` to navigate to the activity.
4. Create a provider function for `IBackendService` with dagger. You have to decide whether you should put it in the `ActivityModule` as a singleton or in the `ActivityModule` as an activity scoped dependency.
  1. Use the following block of code to create an instance of IBackendService:
  ```
  OkHttpClient httpClient = new OkHttpClient.Builder()
          .addNetworkInterceptor(new StethoInterceptor())
          .build();

  return new Retrofit.Builder()
          .client(httpClient)
          .baseUrl("http://jsonplaceholder.typicode.com")
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
          .build()
          .create(IBackendService.class);
  ```
5. Create a view model with a dependency to IBackendService.
6. Inject the view model into the activity.
7. Create a layout that displays the following fields: 
  1. length of the longest post
  2. the body text of the longest post
  3. the name of the user who posted it.
8. Create properties in the view model for the information to be displayed.
9. Use data-binding to display the properties in the view.
10. Create a unit test for your view model to make sure it works correctly.
11. (optional) Check with Stetho that you are not making more than one request to each endpoint.

#### Scroll down for hints to the exercises

...

...

...

...

...

...

...

...

...

...

...

...

...

...

...

## <a href="hints"></a>Hints

### Exercise 1
Your view models should try to avoid dependencies to Android platform components like Context and Intent. In unit tests, they are stubbed but hard to test if you don't control the stubbing/mocking process. 

Use `NavigationService` to get around this problem.

### Exercise 2
First, you need to create a binding in the `TextView` element's `android:textColor` property to refer to the view model's capsule property's coffee type value. Use the `@{}` notation to create the binding.

However, your view does not know how to display state information in your view model (such as the `CoffeeType` property), so you need to provide an adapter to do type conversion. 

Create a `BindingAdapter` for example in the `StaticBindingExampleActivity`. Declare it to apply to attributes of type `"android:textColor"`. The function signature should accept a `TextView` to be modified and an argument of type  `StaticBindingExampleViewModel.CoffeeType` to convert into an `android.graphics.Color`.

### Exercise 3
#### Step 1
The app crashes because it is are trying to update the UI from outside the UI thread. You should apply the `observeOn()` function to each stream that updates the UI, right before the UI update. Give `AndroidSchedulers.mainThread()` as an argument to `observeOn()`.
#### Step 2
You need to bind click handlers to the button. Check `MainActivity` and its layout file for an example of how it can be accomplished.
#### Step 3
You should ensure that the `.map()` step containing the `Thread.sleep()` call is executed outside of the UI thread. You can do this by ensuring that all stages after the `calculateSubject` are running on `Schedulers.computation()` by using `observeOn()`.

### Exercise 4
#### Step 1
Just follow how the `cpuUsage` property was implemented (note the trailing `.get` in the layout file when binding the contents into the `android:text`). For keeping track of the max value, you could use the [scan](http://reactivex.io/documentation/operators/scan.html) operator.
#### Step 2
If you have directly subscribed your max CPU `RxProperty` to `systemMonitorService.getCpuUtilizationStream()`, then you are effectively creating duplicate parallel streams to the CPU utilization reading code. That means that the `SystemMonitorService.executeTop()` method is called once for both the CPU and max CPU properties every time it is updated. If you first apply the `share()` operator to the `Observable` from `getCpuUtilizationStream()`, both subscriptions will share the same connection and trigger execution of `executeTop()` only once.
#### Step 3
Use the `@{}` notation to check when `vm.customInput.get.length()` is greater than 5 for choosing which color to set.
