package fr.rhodless.hcfcore.file;

public enum CooldownsValues {

    PEARL(15),
    GOPPLE(15),
    CRAPPLE(15);

    private final int rawTime;

    CooldownsValues(int time) {
        this.rawTime = time;
    }

    public int getRawTime() {
        return rawTime;
    }

    public int getTime() {
        return FileManagement.COOLDOWNS.getConfig().getInt(name().replace('_', '.'));
    }

}
