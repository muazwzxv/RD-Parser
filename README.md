
### Assigment for my compilers class

We are required to implement a Recursive Descent Parser using java and the best thing about this is
we can decide on the vocab of the parser

- The objective is to create a Recursive Descent Parser using Java
- The vocab can be anything we decided on

### Plan

- My plan is to parse a valid assigment statement in a programming language
```ts
type data = 10;
```
- The assingment statement has to include an identifier, the assignment '=' symbol and the value to
	assign, and ends with a semicolon

### My hope

- If i got lucky, i want the Parser to accept incoming string of mathematical operations and not
	just simple assingment statements

```ts
let data = x + y;
const test = x * y;
var huhu = x / y;
let mod = x % y;
```

### Rules for the parser
Categories of expression in the parser

- I = Identifier :- { let, const, var }
- V = variable :- { free for user to input }
- O = operator :- { +, -, %, / }
- v = value :- {any number input by the user, any string input by the user}
- C = Comparitor :- { = }

A -> IVCv | IV | IVCvOv <br /> 
B -> VCv | VCvOv


