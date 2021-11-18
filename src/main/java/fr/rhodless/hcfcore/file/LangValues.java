package fr.rhodless.hcfcore.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum LangValues {

    DEATH_PLAYER("&c<player>&8[&c<player_kills>&8] &7was slain by &c<killer>&8[&c<killer_kills>&8]"),
    DEATH_NATURAL("&c<player>&8[&c<player_kills>&8] &7died"),

    COOLDOWN("&cYou still have a cooldown of &c&l<cooldown> &cseconds"),
    TALIPEARLS(true),
    PLAYERFORMAT("<player>&f[&a<player_kills>&f]"),
    ALPHANUMERIC("&cName must be alphanumeric"),
    ATLEASTTHREECARACTERS("&cName must be at least 3 caracters"),
    ATMOSTSIXTEENCARACTERS("&cName must be maximum 16 caracters"),

    TERRITORY_CANTBUILD("&fYou cannot build in the territory of <territory>"),
    TERRITORY_CANTINTERACT("&fYou cannot do this in the territory of <territory>"),
    TERRITORY_JOIN("&eNow entering: <territory> &e(<deathban>&e)"),
    TERRITORY_LEAVE("&eNow leaving: <territory> &e(<deathban>&e)"),
    TERRITORY_ROLLBACK(false),

    FACTIONHELP_ONE(Arrays.asList(
            "&8&m----------------------------------------",
            "&7Faction Help (Page &f1/4)",
            " /f show &8- &7Get details about a faction.",
            " /f list &8- &7See a list of all factions.",
            " /f top &8- &7Views of the factions with the largest number of points.",
            " /f create &8- &7Create a faction.",
            " /f rename &8- &7Change the name of your faction.",
            " /f open &8- &7Opens the faction to the public.",
            " /f invite &8- &7Invite a player to the faction.",
            " /f invites &8- &7View factions invitations.",
            " /f uninvite &8- &7Revoke an invitation to a player",
            "&6To view other pages &f/f help #page",
            "&8&m----------------------------------------"
    )),

    FACTION_FACTIONINVITES_MYINVITES("&eYour Invites &7(<size>)&e: &f<invites>"),
    FACTION_FACTIONINVITES_MYFACTION("&e<faction> invites &7(<size>)&e: &f<invites>"),
    FACTION_LIST_HEADER(Arrays.asList(
            "&8&m----------------------------------------",
            "&7Faction List &f(Page <page>/<total_pages>)"
    )),
    FACTION_LIST_FOOTER(Arrays.asList(
            "&6To view other pages&f /f list #page",
            "&8&m----------------------------------------"
    )),
    FACTION_LIST_FORMAT(" &f<position>. &c<name> &7[&a<online>§7/<size> Online] [&a<dtr>&7/<max_dtr> DTR]"),
    FACTION_TOP_HEADER(Arrays.asList(
            "&8&m----------------------------------------",
            " &2&lFaction Top"
    )),
    FACTION_TOP_FOOTER(Collections.singletonList(
            "&8&m----------------------------------------"
    )),
    FACTION_TOP_FORMAT(" &f<position>. <name> &a<points> &7Points"),
    FACTION_INFO(Arrays.asList(
            "&8&m----------------------------------------",
            "&6<faction_name> &7[<online>/<total_members>] &fHome: &7<home>",
            " &fLeader: <leader_format>",
            " &fCaptains: <captains_format>",
            " &fCo-Leaders: <co_leaders_format>",
            " &fMembers: <members_format>",
            " &fTotal Kills: &7<kills>",
            " &fBalance: $&7<balance>",
            " &fDTR: <dtr><dtr_status>",
            " &fPoints: &7<points>",
            "&8&m----------------------------------------"
    )),
    FACTION_ALREADYEXISTING("&cThis faction already exists"),
    FACTION_NOTEXISTING("&cThis faction doesn''t exist"),
    FACTION_ALREADYINFACTION("&cYou are already in a faction"),
    FACTION_TARGETINFACTION("&c<player> &fis already in a faction"),
    FACTION_NOTINFACTION("&cYou are not in a faction"),
    FACTION_NOTLEADER("&cYou are not leader of this faction"),
    FACTION_RENAMESUCCESS("&fYou have renamed the faction to &a<name>"),
    FACTION_CREATE_BROADCAST(true),
    FACTION_CREATE_MESSAGE("&fThe faction &a<faction> &fwas created by &a<player>"),
    FACTION_DISBAND_BROADCAST(true),
    FACTION_DISBAND_MESSAGE("&fThe faction &c<faction> &fwas deleted by &c<player>"),
    FACTION_INVITE_SUCCESS("&a<player> &fhas been invited to the faction."),
    FACTION_INVITE_RECEIVE("&a<player> &fhas invited you to the faction &6<faction_name>"),
    FACTION_INVITE_ACCEPT("&a[Click here] &7to join the faction"),
    FACTION_INVITE_ALREADYINVITED("&fYou already invited &c<player>"),
    FACTION_INVITE_NOTINVITED("&cYou didn''t receive an invite from this faction."),
    FACTION_INVITE_JOIN("&a<player> &fjoined the faction."),
    FACTION_UNINVITE_NOTINVITED("&c<player> &fis not in the invite list"),
    FACTION_UNINVITE_MESSAGE("&c<player> &fhas been uninvited to the faction");

    private String rawMessage;
    private List<String> rawList;
    private boolean rawValue;

    LangValues(String message) {
        this.rawMessage = message;
    }

    LangValues(List<String> list) {
        this.rawList = list;
    }

    LangValues(boolean value) {
        this.rawValue = value;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public List<String> getRawList() {
        return rawList;
    }

    public boolean getRawValue() {
        return rawValue;
    }

    public String getMessage() {
        return FileManagement.LANG.getConfig().getString(name().replace('_', '.'));
    }

    public List<String> getList() {
        return FileManagement.LANG.getConfig().getStringList(name().replace('_', '.'));
    }

    public boolean isValue() {
        return FileManagement.LANG.getConfig().getBoolean(name().replace('_', '.'));
    }
}
