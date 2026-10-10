import java.util.*;

enum State { OPEN, JUDGING, PUBLISHED }

interface ScoringRule {
    double computeScore(double idea, double execution, double presentation);
}

class InnovationTrackRule implements ScoringRule {
    @Override
    public double computeScore(double idea, double execution, double presentation) {
        return idea * 0.50 + execution * 0.30 + presentation * 0.20;
    }
}

class OpenTrackRule implements ScoringRule {
    @Override
    public double computeScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Score {
    private double idea;
    private double execution;
    private double presentation;

    public Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    public double getIdea() { return idea; }
    public double getExecution() { return execution; }
    public double getPresentation() { return presentation; }

    public void setIdea(double idea) { this.idea = idea; }
    public void setExecution(double execution) { this.execution = execution; }
    public void setPresentation(double presentation) { this.presentation = presentation; }
}

class Project {
    private String title;
    private Score score;

    public Project(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public Score getScore() { return score; }
    public void setScore(Score score) { this.score = score; }
}

class Team {
    private String teamName;
    private List<Student> members;
    private ScoringRule trackRule;
    private String trackName;
    private Project project;

    public Team(String teamName, List<Student> members, ScoringRule trackRule, String trackName) {
        this.teamName = teamName;
        this.members = members;
        this.trackRule = trackRule;
        this.trackName = trackName;
    }

    public String getTeamName() { return teamName; }
    public List<Student> getMembers() { return members; }
    public String getTrackName() { return trackName; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public double getFinalScore() {
        if (project != null && project.getScore() != null) {
            Score s = project.getScore();
            return trackRule.computeScore(s.getIdea(), s.getExecution(), s.getPresentation());
        }
        return 0.0;
    }
}

class Hackathon {
    private State state = State.OPEN;
    private List<Team> teams = new ArrayList<>();
    private Set<String> registeredStudentNames = new HashSet<>();

    public void registerTeam(String teamName, List<Student> members, ScoringRule rule, String trackName) {
        if (state != State.OPEN) {
            System.out.println("Registration closed.");
            return;
        }
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }
        for (Student s : members) {
            if (registeredStudentNames.contains(s.getName())) {
                System.out.println("Registration failed: Student " + s.getName() + " is already in another team.");
                return;
            }
        }
        for (Student s : members) {
            registeredStudentNames.add(s.getName());
        }
        Team team = new Team(teamName, members, rule, trackName);
        teams.add(team);
        System.out.println("Team " + teamName + " registered (" + members.size() + " members, " + trackName + " track).");
    }

    public void submitProject(String teamName, String projectTitle) {
        Team team = findTeam(teamName);
        if (team != null) {
            team.setProject(new Project(projectTitle));
            System.out.println("Project '" + projectTitle + "' submitted by " + teamName + ".");
        }
    }
