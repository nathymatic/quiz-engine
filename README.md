# Quiz Engine

> a small JSON-backed quiz engine written in Java because apparently reading questions and checking answers needed an architecture

## About

`Quiz Engine` is a small command-line quiz application written in `Java`!

i made this mainly to practice working with:

- Java project structure
- Maven
- object-oriented design
- JSON serialization and deserialization
- file I/O
- repository patterns
- CLI input handling
- separating application layers
- exception handling

the project stores quizzes in JSON and converts them into Java objects using `Jackson`.

## Features

- JSON-backed quizzes
- multiple-choice questions
- exactly four answers per question
- one correct answer per question
- categories containing questions
- quiz sessions with score tracking
- CLI-based interaction
- JSON serialization and deserialization
- Maven project management
- separated model, service, repository, and UI layers

### Model

the model classes represent the actual quiz data:

- `Question` — a question, its four answers, and the correct answer
- `Category` — a category containing questions
- `Quiz` — a named quiz associated with a category (may change it to include a collection of categories for variety)

### Repository

the repository layer handles persistence:

- `QuizRepository` — defines how quizzes can be loaded and saved
- `JsonQuizRepository` — implements the repository using JSON files and `Jackson`

### Service

`QuizSession` handles the state of an active quiz:

- current question
- current question index
- score
- checking answers
- determining whether the quiz is finished

### UI

`ConsoleUi` handles communication with the user through the terminal.

`App` acts as the entry point and wires the different components together.

## JSON Format

quizzes are stored in `quizzes.json`.

a quiz contains a category, and the category contains its questions:

```json
[
    {
        "name": "Rust Basics",
        "category": {
            "name": "Programming",
            "questions": [
                {
                    "text": "What does Option<T> represent in Rust?",
                    "answers": [
                        "A value that may be absent",
                        "A mutable reference",
                        "A collection",
                        "A guaranteed value"
                    ],
                    "correctIndex": 0
                }
            ]
        }
    }
]
```

`correctIndex` is zero-based, so:

```text
0 → first answer
1 → second answer
2 → third answer
3 → fourth answer
```

and if you use Lua/Luau, then.... no...

## How It Works

the basic flow is:

```text
quizzes.json
     ↓
JsonQuizRepository
     ↓
Jackson
     ↓
Quiz / Category / Question
     ↓
QuizSession
     ↓
ConsoleUi
     ↓
user answers questions
     ↓
score
```

woohoo!!!

so when the application starts, `JsonQuizRepository` loads the JSON file and Jackson deserializes it into Java objects.

a `QuizSession` then keeps track of the current question and score while `ConsoleUi` handles the actual terminal interaction.

so the UI doesn't need to know how JSON works, and the repository doesn't need to know how the quiz is played.

which is nice because responsibilities exist and everyone's allowed to live peacefully.

## Example

running the application currently looks something like:

```text
What does Option<T> represent in Rust?
1. A value that may be absent
2. A mutable reference
3. A collection
4. A guaranteed value
Answer: 1

Correct!

Final score: 1/1
Goodbye!
```

## Requirements

- Java 26 (I'M SORRY.... but i'm updated teehee wait no idk)
- Maven

## Running

clone the repository:

```powershell
git clone https://github.com/jayywashere/quiz-engine
cd quiz-engine
```

build the project:

```powershell
mvn clean package
```

run the application:

```powershell
mvn exec:java '-Dexec.mainClass=com.jay.quiz.ui.App'
```

the first run may download Maven plugins and dependencies. soo, umm, subsequent runs should be much less dramatic!

## Current Scope

currently, the application can:

- load quizzes from JSON
- deserialize them into Java objects
- run a quiz through the CLI
- validate answers
- track the score
- save quizzes through the repository abstraction

future features may include:

- creating questions through the CLI
- creating categories
- selecting quizzes
- editing existing quizzes
- better input validation
- more comprehensive error handling
- tests
- more flexible quiz configuration

## Why I Made This

i wanted a smaller Java project that wasn't just:

```text
read input
do thing
print result
repeat
```

(YET AGAIN)

and i specifically wanted to learn the Java equivalents of things i already know how to do in C#.

especially JSON serialization.

because apparently knowing how to do something in one language does not magically transfer the syntax into another language. how, tragic. (OMORI REFERENCE???)

and uhmmmm... i was hoping for this to be a mock quiz to act as a reviewer about a topic.. but, yeah.. other apps have it BETTER!! (i'm not trying to compete but, HEY. siphon at least some value from my projects)

## License

See [LICENSE](LICENSE).
