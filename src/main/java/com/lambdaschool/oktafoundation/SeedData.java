package com.lambdaschool.oktafoundation;

import com.lambdaschool.oktafoundation.models.*;
import com.lambdaschool.oktafoundation.repository.MemberReactionRepository;
import com.lambdaschool.oktafoundation.repository.ReactionRepository;
import com.lambdaschool.oktafoundation.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CommandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;
    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    ProgramService programService;

    @Autowired
    ClubService clubService;

    @Autowired
    ReactionsService reactionsService;

    @Autowired
    MemberService memberService;

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    MemberReactionRepository memberReactionRepository;


    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        clubService.deleteAll();
        programService.deleteAll();
        reactionsService.deleteAll();
        memberService.deleteAll();



        Role r1 = new Role("admin");
        Role r2 = new Role("cd");
        Role r3 = new Role("ydp");
        Role r4 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);
        r4 = roleService.save(r4);

        Member m1 = new Member("TestMember1");
        Member m2 = new Member("TestMember2");
        Member m3 = new Member("TestMember3");
        Member m4 = new Member("TestMember4");

        memberService.save(m1);
        memberService.save(m2);
        memberService.save(m3);
        memberService.save(m4);

        Reactions re1 = new Reactions("1F600", "Happy");
        Reactions re2 = new Reactions("1F62E", "Wow");
        Reactions re3 = new Reactions("1F971", "Bored");
        Reactions re4 = new Reactions("1F974", "Meh");
        Reactions re5 = new Reactions("1F634", "Sleepy");
        Reactions re6 = new Reactions("1F622", "Sad");
        Reactions re7 = new Reactions("263A", "Embarrassed");
        Reactions re8 = new Reactions("1F60C", "Shy");
        Reactions re9 = new Reactions("1F60A", "Good");
        Reactions re10 = new Reactions("1F620", "Mad");
        Reactions re11 = new Reactions("1F915", "Sick");
        Reactions re12 = new Reactions("1F60E", "Chill");


        re1 = reactionsService.save(re1);
        re2 = reactionsService.save(re2);
        re3 = reactionsService.save(re3);
        re4 = reactionsService.save(re4);
        re5 = reactionsService.save(re5);
        re6 = reactionsService.save(re6);
        re7 = reactionsService.save(re7);
        re8 = reactionsService.save(re8);
        re9 = reactionsService.save(re9);
        re10 = reactionsService.save(re10);
        re11 = reactionsService.save(re11);
        re12 = reactionsService.save(re12);

        // Super Admin
        User u1 = new User("llama001@maildrop.cc");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        userService.save(u1);


        // Club Directors
        User u2 = new User("llama002@maildrop.cc");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        userService.save(u2);

        User u3 = new User("llama003@maildrop.cc");
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        userService.save(u3);

        User u4 = new User("llama004@maildrop.cc");
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));
        userService.save(u4);

        // Youth Development Professionals
        User u5 = new User("llama005@maildrop.cc");
        u5.getRoles()
            .add(new UserRoles(u5,
                r3));
        userService.save(u5);

        User u6 = new User("llama006@maildrop.cc");
        u6.getRoles()
            .add(new UserRoles(u6,
                r3));
        userService.save(u6);

        User u7 = new User("llama007@maildrop.cc");
        u7.getRoles()
            .add(new UserRoles(u7,
                r4));
        userService.save(u7);

        Program p1 = new Program("Club Checkin");
        Program p2 = new Program("Arts & Crafts");
        Program p3 = new Program("Archery");
        Program p4 = new Program("Basketball");
        Program p5 = new Program("Homework Help");
        Program p6 = new Program("Music");
        Program p7 = new Program("Soccer");
        Program p8 = new Program("Club Checkout");
        p1 = programService.save(p1);
        p2 = programService.save(p2);
        p3 = programService.save(p3);
        p4 = programService.save(p4);
        p5 = programService.save(p5);
        p6 = programService.save(p6);
        p7 = programService.save(p7);
        p8 = programService.save(p8);

        Club c1 = new Club( "club1", "llama002@maildrop.cc");
        c1.getProgram()
            .add(new ClubPrograms(c1,p1));
        c1.getProgram()
            .add(new ClubPrograms(c1,p2));
        c1.getProgram()
            .add(new ClubPrograms(c1,p3));
        c1.getProgram()
            .add(new ClubPrograms(c1,p4));
        c1.getProgram()
            .add(new ClubPrograms(c1,p5));
        c1 = clubService.save(c1);

        Club c2 = new Club( "club2", "llama003@maildrop.cc");
        c2.getProgram()
            .add(new ClubPrograms(c2,p1));
        c2.getProgram()
            .add(new ClubPrograms(c2,p2));
        c2.getProgram()
            .add(new ClubPrograms(c2,p4));
        c2 = clubService.save(c2);

        Club c3 = new Club( "club3",  "llama004@maildrop.cc");
        c3.getProgram()
            .add(new ClubPrograms(c3,p1));
        c3.getProgram()
            .add(new ClubPrograms(c3,p2));
        c3.getProgram()
            .add(new ClubPrograms(c3,p3));
        c3.getProgram()
            .add(new ClubPrograms(c3,p4));
        c3 = clubService.save(c3);

        // hard coding club data for addition of programs by csv file
        // associating programs with clubname for many to many relationship
        // convert to form, CSV, or integration with stakeholder management system in future release

        Club c4 = new Club( "anderson", "andrew lorenzo");
        c4 = clubService.save(c4);
        Club c5 = new Club( "grossman", "henry segovia");
        c5 = clubService.save(c5);
        Club c6 = new Club( "jefferson", "jennifer wissusik");
        c6 = clubService.save(c6);
        Club c7 = new Club( "johnston", "jennifer wissusik");
        c7 = clubService.save(c7);
        Club c8 = new Club( "morton", "lisa barron");
        c8 = clubService.save(c8);
        Club c9 = new Club( "notter", "leslie chicas");
        c9 = clubService.save(c9);
        Club c10 = new Club( "catlin", "");
        c10 = clubService.save(c10);
        Club c11 = new Club( "marley", "");
        c11 = clubService.save(c11);
        Club c12 = new Club( "stelle", "");
        c12 = clubService.save(c12);

//          Trying to get this seed data below to work to test member reactions
        Club[] clist = {c1, c2};
        var ran = new Random();
        var allmem = memberService.findAll();
        ArrayList<Reactions> allreactions = new ArrayList<>();
        reactionRepository.findAll().iterator().forEachRemaining(allreactions::add);
        var cas = new ArrayList<ClubPrograms>();
        Arrays.stream(clist).forEach(i -> cas.addAll(i.getProgram()));

        for (int i = 0; i < 300; i++) {
            var curmem = allmem.get(ran.nextInt(allmem.size()));
            var curca = cas.get(ran.nextInt(cas.size()));
            MemberReactions mr;

            if (curca.getProgram().getName().equals("Club Checkin") ||
            curca.getProgram().getName().equals("Club Checkout")) {
                mr = memberReactionRepository.save(new MemberReactions(
                        curmem, allreactions.get(ran.nextInt(allreactions.size())), curca
                ));
            } else {
                    mr = memberReactionRepository.save(new MemberReactions(
                            curmem,
                            allreactions.get(ran.nextInt(allreactions.size())),
                            curca
                    ));
            }

            curmem.getReactions().add(mr);
            curmem.getClubs().add(new ClubMembers(c1, curmem));
            memberService.save(curmem);
        }





    }
}