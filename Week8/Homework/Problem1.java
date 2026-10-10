import java.util.*;

abstract class Question {
    private String text;
    private String correctAnswer;

    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getText() { return text; }
    public String getCorrectAnswer() { return correctAnswer; }

    public abstract boolean evaluate(String submittedAnswer);
}

class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String text, String correctAnswer) {
        super(text, correctAnswer);
    }

    @Override
    public boolean evaluate(String submittedAnswer) {
        return getCorrectAnswer().equalsIgnoreCase(submittedAnswer);
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() { return questions; }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean isSubmitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        System.out.println("Examination '" + examination.getTitle() + "' started by " + student.getName() + ".");
    }

    public void answerQuestion(int questionIndex, String answer) {
        if (isSubmitted) {
            System.out.println("Cannot change answer: Examination attempt already submitted.");
            return;
        }
        answers.put(questionIndex, answer);
        System.out.println("Question " + (questionIndex + 1) + " answered with '" + answer + "'.");
    }

    public void submit() {
        if (isSubmitted) {
            System.out.println("Attempt already submitted.");
            return;
        }
        isSubmitted = true;
        System.out.println("Examination '" + examination.getTitle() + "' submitted successfully.");
        evaluate();
    }

    private void evaluate() {
        List<Question> questions = examination.getQuestions();
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            String ans = answers.get(i);
            if (ans != null && questions.get(i).evaluate(ans)) {
                score++;
            }
        }
        System.out.println("Result for '" + examination.getTitle() + "' attempt: " + score + "/" + questions.size() + " correct.");
    }
}

public class Question1Main {
    public static void main(String[] args) {
        Examination mathQuiz = new Examination("Math Quiz");
        mathQuiz.addQuestion(new MultipleChoiceQuestion("What is 2+2?", "A"));
        mathQuiz.addQuestion(new MultipleChoiceQuestion("What is 3*3?", "B"));

        Student student = new Student("Student");
        Attempt attempt = new Attempt(student, mathQuiz);

        attempt.answerQuestion(0, "A");
        attempt.answerQuestion(1, "C");
        attempt.submit();
    }
}
