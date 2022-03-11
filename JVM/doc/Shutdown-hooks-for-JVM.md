# JVM

## Adding Shutdown Hooks for JVM Applications

### 1. Overview

Sometimes we need to have a plan for gracefully shutting a service. Here we will focus on different ways a JVM
application can terminate. We could use Java APIs to manage shutdown hooks.

### 2. JVM Shutdown

The JVM can be shutdown in two different ways:

1. A controller process 
2. An abrupt manner

**A controller process shuts down the JVM when either**:

    * The last non-daemon thread terminated. For example, when the main thread exits, the JVM starts its shutdown
      process
    * Sending an interrupt signal from the OS. For instance, by pressing Ctrl + C or logging off the OS
    * Calling System.exit() from Java code.

While we strive for graceful shutdowns, sometimes the JVM may shut down in an abrupt and unexpected manner. **The JVM
shuts down abruptly when**:

    * Sending a kill signal from the OS. For example, by issuing a kill -9 <jvm_pid>
    * Calling Runtime.getRuntime().halt() from Java code.
    * The host OS dies unexpectedly, for instance, in a power failure or OS panic.

### 3. Shutdown Hooks

The JVM allows registering functions to run before it completes it's shut down. The functions are also a good place for
releasing resources or other similar home-keeping tasks. In JVM terminology, **these functions are called shutdown
hooks**.

**Shutdown hooks are basically initialized but not started threads**. When the JVM begins its shutdown process, it will
start all registered hooks in an unspecified order. After running all hooks, the JVM will halt.

#### 3.1 Adding Hooks

```
    @Test
    public void givenAHook_WhenShutsDown_ThenHookShouldBeExecuted() {
        Thread hook = new Thread(() -> System.out.println("In the middle of a shutdown"));
        Runtime.getRuntime().addShutdownHook(hook);
    }
```

The JVM is responsible for starting hook threads. Therefore, if the given hook has been already started, Java will throw
an exception.

```
    @Test
    public void addingAHook_WhenThreadAlreadyStarted_ThenThrowsAnException() {
        Thread runningHook = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        runningHook.start();
        assertThatThrownBy(() -> Runtime.getRuntime().addShutdownHook(runningHook)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("Hook already running");
    }
```

Obviously, we also can't register a hook multiple times:

```
    @Test
    public void addingAHook_WhenAlreadyExists_ThenAnExceptionWouldBeThrown() {
        Thread hook = new Thread(() -> {
        });
        Runtime.getRuntime().addShutdownHook(hook);

        assertThatThrownBy(() -> Runtime.getRuntime().addShutdownHook(hook)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("Hook previously registered");
    }
```

3.2 Removing hooks

Java provides a method to remove a particular shutdown hook after registering it.

```
    @Test
    public void removeAHook_WhenItIsAlreadyRegistered_ThenWouldDeRegisterTheHook() {
        Thread hook = new Thread(() -> System.out.println("Won't run!"));
        Runtime.getRuntime().addShutdownHook(hook);

        assertThat(Runtime.getRuntime().removeShutdownHook(hook)).isTrue();
    }
```

3.3 Caveats

**The JVM runs shutdown hooks only in case of normal terminations**. So, when an external force kills the JVM process
abruptly, the JVM won't get a chance to execute shutdown hooks. Additionally, halting the JVM from Java code will also
have the same effect:

```
Thread haltedHook = new Thread(() -> System.out.println("Halted abruptly"));
Runtime.getRuntime().addShutdownHook(haltedHook);
        
Runtime.getRuntime().halt(129);
```

The halt method forcibly terminates the currently running JVM. Therefore, registered shutdown hooks won't get a chance
to execute.