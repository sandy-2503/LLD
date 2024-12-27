abstract class Game {
    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

class Cricket extends Game {
    void initialize() {
        System.out.println("Cricket game initialized");
    }

    void startPlay() {
        System.out.println("Cricket game started");
    }

    void endPlay() {
        System.out.println("Cricket game ended");
    }
}

class Football extends Game {
    void initialize() {
        System.out.println("Football game initialized");
    }

    void startPlay() {
        System.out.println("Football game started");
    }

    void endPlay() {
        System.out.println("Football game ended");
    }
}

public class TemplateMethodPattern {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();

        game = new Football();
        game.play();
    }
}
