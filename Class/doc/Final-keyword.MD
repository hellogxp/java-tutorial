## final keywords

Generally speaking, this keyword means "immutable", but it still needs to be judged according to the context. This
keywords cab be used for: **<font color=red>data</font>**, **<font color=red>methods</font>**, **<font color=red>
class</font>**.

### final data

**Primitive**: Final makes the value a constant. It tells the compiler that a piece of data is "constant", it can be a
compiler-time constant sometimes, in this case, the variable could be replaced with a constant value at compile time
rather than at run time. **A value must be given at the time of definition of such a constant**.

**Object reference**: Final make the **reference** not the value instead of the value it points to a constant, once the
reference is initialized to an object, it can never be changed to pointed to another object. However, the object itself
can be modified.

> Note that a field that is both static and final has only one piece of storage that cannot be changed.
> Note that by convention, fields that are both static and final (that is, compile-time constants) are capitalized and
> use underscores to separate words.

### Blank finals

Java allows fields are declared as final but are not given an initialization value. **However, the blank final must be
initialized before it is used (The compiler ensures this).**, so you are forced to perform assignments either with an
**expression** at the point of definition of the field or in every constructor. This usage is very flexible.

### final arguments

We can make arguments final by declaring them in argument list. This means that inside the method you cannot change what
the argument reference points to. _This feature is primarily used to pass data to anonymous inner class_.

```java
public class FinalArguments {
    void with(final Gizmo g) {
        //! g = new Gizmo(); // Illegal -- g is final
    }

    void without(Gizmo g) {
        g = new Gizmo(); // OK -- g not final
        g.spin();
    }

    // void f(final int i) { i++; } // Can't change
    // You can only read from a final primitive:
    int g(final int i) { return i + 1; }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }
} ///:~

```

### final methods

There are two reasons for final methods.

1. To put a "lock" on the method to prevent any inheriting class from changing its meaning.
2. ~~Efficiency (In earlier implementation of Java)~~.

### final and private

Any private methods in a class are implicitly final. Because you can't access a private method, you can't override it.
You can add the final specifier to a private method, but it doesn't give that method any extra meaning.

This issue can cause confusion, because if you try to override a private method (which is implicitly final), it seems to
work, and the compiler doesn't give an error message.

```java
class WithFinals {
    // Identical to "private" alone:
    private final void f() { print("WithFinals.f()"); }

    // Also automatically "final":
    private void g() { print("WithFinals.g()"); }
}

class OverridingPrivate extends WithFinals {
    private final void f() {
        print("OverridingPrivate.f()");
    }

    private void g() {
        print("OverridingPrivate.g()");
    }
}

class OverridingPrivate2 extends OverridingPrivate {
    public final void f() {
        print("OverridingPrivate2.f()");
    }

    public void g() {
        print("OverridingPrivate2.g()");
    }
}

public class FinalOverridingIllusion {
    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
        // You can upcast:
        OverridingPrivate op = op2;
        // But you can't call the methods:
        //! op.f();
        //! op.g();
        // Same here:
        WithFinals wf = op2;
        //! wf.f();
        //! wf.g();
    }
} /* Output:
OverridingPrivate2.f()
OverridingPrivate2.g()
*///:~
```

"Overriding" can only occur if something is part of the base-class interface. That is, you must be able to upcast an
object to its base type and call the same method. If a method is private, it isn't part of the base-class interface. It
is just some code that's hidden away inside the class, and it just happens to have the name, but if you create a public,
protected, or package-access method with the same name in the derived class, there's no connection to the method that
might happen to have that name in the base class. You haven't overridden the method; You've just create a new method.
Since a private method is unreachable and effectively invisible, it doesn't factor into anything except for the code
organization of the class for which it was defined.

### final classes

The class declared by the final can't be inherited or changed, all methods in a final class are implicitly final, since
there is no way to override them.