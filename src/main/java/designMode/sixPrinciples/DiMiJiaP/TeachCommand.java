package designMode.sixPrinciples.DiMiJiaP;

import java.util.ArrayList;
import java.util.List;

class Girl {
}

class GroupLeader {
    private List girls;

    public GroupLeader(List girls) {
        this.girls = girls;
    }

    public void countGirls() {
        System.out.println("女生数量是: " + girls.size());
    }
}

class Teacher {
    public void command(GroupLeader groupLeader){
        groupLeader.countGirls();
    }
}

public class TeachCommand {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        List<Girl> girls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            girls.add(new Girl());
        }

        teacher.command(new GroupLeader(girls));
    }
}
