public enum Priority {

    HIGH(3), MEDIUM(2), LOW(1);

    private final int levelOfPriority;

    Priority(int levelOfPriority) {
        this.levelOfPriority = levelOfPriority;
    }

    public int getLevelOfPriority() {

        return this.levelOfPriority;
    }

}
