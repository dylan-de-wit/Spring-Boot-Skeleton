package com.dylandewit.skeleton.database;


import com.dylandewit.skeleton.api.base.seeders.Seeder;
import com.dylandewit.skeleton.api.permission.seeders.PermissionSeeder;
import com.dylandewit.skeleton.api.role.seeders.RoleSeeder;
import com.dylandewit.skeleton.api.user.seeders.FakeUserSeeder;
import com.dylandewit.skeleton.api.user.seeders.UserSeeder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DatabaseSeeder {
    private final List<Seeder> necessarySeeders = new ArrayList<>();
    private final List<Seeder> fakeDataSeeders = new ArrayList<>();

    @Autowired
    public DatabaseSeeder(@Value("${com.dylandewit.skeleton.seed-necessary-data}") boolean seedNecessary,
                          @Value("${com.dylandewit.skeleton.seed-fake-data}") boolean seedFake,
                          PermissionSeeder permissionSeeder,
                          RoleSeeder roleSeeder,
                          UserSeeder userSeeder,
                          FakeUserSeeder fakeUserSeeder) {
        if (seedFake)
            fakeDataSeeders.addAll(List.of(fakeUserSeeder));

        if (seedNecessary)
            necessarySeeders.addAll(List.of(permissionSeeder, roleSeeder, userSeeder));
    }

    @Transactional
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (necessarySeeders.isEmpty() && fakeDataSeeders.isEmpty())
            return;

        log.info("Seeding database...");
        seed(necessarySeeders, "Seeding necessary data...");
        seed(fakeDataSeeders, "Seeding fake data...");
        log.info("Done seeding...");
    }

    private void seed(List<Seeder> seeders, String message) {
        if (seeders.isEmpty())
            return;

        log.info(message);
        seeders.forEach(Seeder::seed);
    }
}
