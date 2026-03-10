# Java Output Solutions

Is file me `Test1.java`, `Test2.java`, aur `ForAll.java` tino programs ka detailed explanation diya gaya hai.
Har question ke liye:

- final output
- step-by-step dry run
- key Java concept
- short conclusion

## 1. Test1.java

### Code Idea

Is program me ek class `A` hai jisme:

- ek static variable `s`
- ek static block
- ek instance initializer block
- ek constructor

ye sab milkar string ko update kar rahe hain.

### Final Output

```text
ABCDCD
```

### Step-by-Step Execution

Initial value:

```java
static String s = "A";
```

Ab class load hote hi static block chalega:

```java
static {
    s = s + "B";
}
```

Ab `s = "AB"`

`main()` me:

```java
new A();
new A();
```

#### First object creation

Object create hote waqt pehle instance block chalta hai:

```java
{
    s = s + "C";
}
```

`s = "ABC"`

Uske baad constructor chalega:

```java
A() {
    s = s + "D";
}
```

`s = "ABCD"`

#### Second object creation

Phir se instance block chalega:

`s = "ABCDC"`

Phir constructor chalega:

`s = "ABCDCD"`

Finally:

```java
System.out.println(A.s);
```

Output:

```text
ABCDCD
```

### Important Concept

- static block sirf ek baar chalta hai, jab class load hoti hai.
- instance block har object creation par chalta hai.
- constructor bhi har object creation par chalta hai.
- order hota hai: static block -> instance block -> constructor

### Conclusion

`B` sirf ek baar add hua kyunki static block ek baar chala.
`C` aur `D` do-do baar add hue kyunki do objects bane.

Isliye final answer `ABCDCD` hai.

---

## 2. Test2.java

### Code Idea

Is program me inheritance use hua hai:

- `Parent` class
- `Child` class jo `Parent` ko extend karti hai

Yahan static block aur constructors dono parent-child relationship ke saath execute ho rahe hain.

### Final Output

```text
PACBDBD
```

### Step-by-Step Execution

Sabse pehle static variable initialize hota hai:

```java
static String s = "P";
```

Ab class loading ke time parent class ka static block chalega:

```java
static {
    s += "A";
}
```

`s = "PA"`

Ab child class ka static block chalega:

```java
static {
    s += "C";
}
```

`s = "PAC"`

`main()` me:

```java
new Child();
new Child();
```

#### First Child object

Jab `Child` object banta hai, to pehle parent constructor chalta hai:

```java
Parent() {
    s += "B";
}
```

`s = "PACB"`

Uske baad child constructor chalega:

```java
Child() {
    s += "D";
}
```

`s = "PACBD"`

#### Second Child object

Phir se parent constructor:

`s = "PACBDB"`

Phir child constructor:

`s = "PACBDBD"`

Finally:

```java
System.out.println(Child.s);
```

Output:

```text
PACBDBD
```

### Important Concept

- parent ka static block child ke static block se pehle chalta hai.
- object creation ke time parent constructor pehle chalta hai.
- uske baad child constructor chalta hai.
- static members inheritance chain me shared rehte hain.

### Conclusion

Execution order yahan ye hai:

1. Parent static block
2. Child static block
3. Parent constructor
4. Child constructor

Do objects banne ki wajah se constructor wala part do baar repeat hua.

Isliye final answer `PACBDBD` hai.

---

## 3. ForAll.java

### Code Idea

Ye program strings, string pool, `intern()`, `StringBuilder`, aur `StringBuffer` ka behavior test karta hai.

### Final Output

```text
false
true
CYY
DZZ
AB
```

### Pehle Utility Class Samajhte Hain

```java
class Util {
    static String s = "A";

    static {
        s += "B";
    }
}
```

Class load hone par:

- `s = "A"`
- static block ke baad `s = "AB"`

Isliye last line me `Util.s` print karne par `AB` aayega.

### `mix()` Method Samajhte Hain

```java
static String mix(String a, StringBuilder b, StringBuffer c) {
    a += "X";
    b.append("Y");
    c.append("Z");

    String t = a + b + c;
    return t.intern();
}
```

Yahan teen alag behavior hain:

- `String a` immutable hota hai, isliye `a += "X"` se original string change nahi hoti, sirf local reference update hota hai.
- `StringBuilder b` mutable hota hai, isliye `append("Y")` original object ko change karta hai.
- `StringBuffer c` bhi mutable hota hai, isliye `append("Z")` original object ko change karta hai.

Aur sabse important:

```java
return t.intern();
```

`intern()` string pool ka reference return karta hai.

---

### `main()` Dry Run

#### Step 1: Initial values

```java
String s1 = "AB";
String s2 = new String("AB");
StringBuilder sb = new StringBuilder("C");
StringBuffer  sf = new StringBuffer("D");
```

- `s1` string pool me stored literal ko refer karta hai.
- `s2` heap me naya object hai.
- `sb = "C"`
- `sf = "D"`

#### Step 2: First call

```java
String r1 = Util.mix(s1, sb, sf);
```

Method ke andar:

- `a = "AB"` se local copy aayi
- `a += "X"` -> `"ABX"`
- `sb.append("Y")` -> `sb = "CY"`
- `sf.append("Z")` -> `sf = "DZ"`

Ab:

```java
String t = a + b + c;
```

`t = "ABXCYDZ"`

`intern()` ke baad `r1` string pool wale `"ABXCYDZ"` ko refer karega.

#### Step 3: Second call

```java
String r2 = Util.mix(s2, sb, sf);
```

Ab current values hain:

- `s2` ka content abhi bhi `"AB"` hai
- `sb = "CY"`
- `sf = "DZ"`

Method ke andar:

- `a += "X"` -> `"ABX"`
- `sb.append("Y")` -> `"CYY"`
- `sf.append("Z")` -> `"DZZ"`

Ab:

`t = "ABX" + "CYY" + "DZZ"`

toh `t = "ABXCYYDZZ"`

`intern()` ke baad `r2` string pool me `"ABXCYYDZZ"` ko refer karega.

---

### Ab Print Statements Samajhte Hain

#### 1. `System.out.println(r1 == r2);`

- `r1` = pooled string `"ABXCYDZ"`
- `r2` = pooled string `"ABXCYYDZZ"`

Dono different strings hain, isliye references bhi different honge.

Output:

```text
false
```

#### 2. `System.out.println(r1 == r3);`

```java
String r3 = "ABXCYDZ";
```

`r3` string literal hai, to ye string pool me rahega.
`r1` bhi `intern()` ki wajah se same pooled string ko refer kar raha hai.

Isliye:

```text
true
```

#### 3. `System.out.println(sb);`

`sb` pe do baar `append("Y")` hua:

- pehli baar -> `CY`
- doosri baar -> `CYY`

Output:

```text
CYY
```

#### 4. `System.out.println(sf);`

`sf` pe do baar `append("Z")` hua:

- pehli baar -> `DZ`
- doosri baar -> `DZZ`

Output:

```text
DZZ
```

#### 5. `System.out.println(Util.s);`

Static block ke baad `Util.s = "AB"`

Output:

```text
AB
```

### Important Concept

- `String` immutable hota hai.
- `StringBuilder` mutable hota hai.
- `StringBuffer` bhi mutable hota hai, bas ye synchronized hota hai.
- `==` reference compare karta hai, content nahi.
- `intern()` string ko string pool se connect karta hai.

### Conclusion

Is program ka sabse important point ye hai ki:

- `s1` aur `s2` dono ka content same hai, lekin objects alag tareeke se bane hain.
- `StringBuilder` aur `StringBuffer` original objects ko modify kar dete hain.
- `intern()` ki wajah se `r1` aur `r3` same pooled reference ko point karte hain.

Isliye final output hai:

```text
false
true
CYY
DZZ
AB
```

---

## Quick Summary

### Test1

- static block ek baar
- instance block do baar
- constructor do baar
- output: `ABCDCD`

### Test2

- parent static
- child static
- parent constructor
- child constructor
- do objects ki wajah se constructor sequence repeat hua
- output: `PACBDBD`

### ForAll

- `String` immutable
- `StringBuilder` aur `StringBuffer` mutable
- `intern()` string pool reference deta hai
- output:

```text
false
true
CYY
DZZ
AB
```