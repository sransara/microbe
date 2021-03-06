# Microbe
**A Micro lang compiler**

The one and only Micro lang compiler that you will need for compiling your precious Micro code in to almighty tiny assembly

Microbe can

- Handle complex expressions
- Build IR
- Build Control Flow Graph
- Do liveness analysis
- Do register allocation
- Produce assembly (tiny)

And also Microbe

- can be easily extended to produce other assembly with (2 or 3 operands)
- IR generation and Tiny Code generation across functions can possibly be parallelized to increase code generation speed

And did you know that microbe can [eat electricity](http://www.iflscience.com/chemistry/scientists-reveal-how-microbe-eats-electricity)?

See some sample micro code
```
#!micro
PROGRAM p
BEGIN
    STRING prompt := "Give me a number to output bitstring in LSB to MSB: ";
    STRING eol := "\n";
    STRING one := "1";
    STRING zero := "0";

    FUNCTION VOID main()
    BEGIN
        INT a;
        WRITE(prompt);
        READ(a);

        WHILE(a > 0)
            IF(IsEven(a) = 1)
                 WRITE(zero);
            ELSE
                WRITE(one);
            ENDIF
            a := a / 2;
        ENDWHILE

        WRITE(eol);
    END

    -- mutually recursive functions
    FUNCTION INT IsEven(INT a)
    BEGIN
        IF(a = 0)
            RETURN 1;
        ELSE
            RETURN IsOdd(a - 1);
        ENDIF
    END

    FUNCTION INT IsOdd(INT a)
    BEGIN
        IF(a = 0)
            RETURN 0;
        ELSE
            RETURN IsEven(a - 1);
        ENDIF
    END
END
```

Follows the specification provided at https://engineering.purdue.edu/~milind/ece573/2014fall/project/

Check under test folder for sample Micro code or how to test Micro
```
#!bash
make
make test
```
---

Side notes (things to read before being alarmed)

- In spite of common standard to use ArrayList you will observe frequent use of LinkedList in IR Generation because of the frequent *adding* to lists instead of *iterating* through the list
- This program is neither a virus nor a bacterium
