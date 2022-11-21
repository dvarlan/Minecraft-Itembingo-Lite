package bingotests.bingotests.team;

import java.util.ArrayList;

public class TeamHandler {

    private final ArrayList<Team> teamlist = new ArrayList<>();

    public TeamHandler() {
    }

    public ArrayList<Team> getTeamlist() {
        return teamlist;
    }

    public void addTeam (Team team) {
        // Team names should be unique when you play with friends
        teamlist.add(team);
    }

    public void removeTeam(Team team) {
        teamlist.remove(team);
    }
}
