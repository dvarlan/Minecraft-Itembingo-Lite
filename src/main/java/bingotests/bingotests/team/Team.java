package bingotests.bingotests.team;


import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    private String teamname;
    private ArrayList<Player> members = new ArrayList<>();

    private Integer score = 0;

    public Team(String teamname) {
        setTeamname(teamname);
        setMembers(members);
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public ArrayList<Player> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Player> members) {
        this.members = members;
    }

    public void addMember(Player member) {
        if(!(members.contains(member))) {
            members.add(member);
        }
    }

    public void removeMember(Player member) {
        members.remove(member);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
