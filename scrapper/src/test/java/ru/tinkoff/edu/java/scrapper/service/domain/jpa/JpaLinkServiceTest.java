package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.service.domain.api.LinkService;

@SpringBootTest
class JpaLinkServiceTest extends IntegrationEnvironment {
    @Autowired
    private LinkService linkService;

    @Autowired
    private ServicesTestHelper helper;

    @Test
    @Transactional
    @Rollback
    void updateLastCheckedTimeAndGet__someLinksUnchecked_updatesUncheckedLinks() {
        // given
        // addLink()
        // [link records, some checked, some unchecked]

        // when
        // List<Link> links = linkService.updateLastCheckedTimeAndGet();

        // then
        // links == unchecked
        // unchecked - updated
        // already checked - not updated
    }

    @Test
    @Transactional
    @Rollback
    void updateLink__linkExists_updatesLink() {
        // given
        // Link link = (add link in db)

        // when

        // then
        // linkService.updateLink(link)
        // Link updatedLink = getLinkById(link.getId())
        // assert updatedLink.getLastCheckTime() > link.getLastCheckTime()
    }

    @Test
    @Transactional
    @Rollback
    void updateLink__linkDoesNotExist_throwsException() {
        // given
        // Link link = (not exists in db)

        // when

        // then
        // linkService.updateLink(link) - throws InternalError
    }
}