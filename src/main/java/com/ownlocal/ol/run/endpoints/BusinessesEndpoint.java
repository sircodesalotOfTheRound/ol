package com.ownlocal.ol.run.endpoints;

import com.ownlocal.ol.entries.Business;
import com.ownlocal.ol.entries.EntryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

import static com.ownlocal.ol.tools.Safe.integer;


@RestController
public class BusinessesEndpoint {
  public static int DEFAULT_PAGE_SIZE = 50;

  @Autowired()
  @Qualifier(EntryServer.ENTRY_SERVER_KEY)
  private EntryServer entryServer;

  @RequestMapping(value="/businesses", method = RequestMethod.GET)
  public @ResponseBody BusinessesResponse listBusinesses(
    @RequestParam(value="page", required=false) Integer page,
    @RequestParam(value="pagesize", required=false) Integer pageSize) {

    return entryServer.getPage(integer(page), integer(pageSize, 50));
  }

  @RequestMapping(value="/businesses/{index}", method = RequestMethod.GET)
  public @ResponseBody Object getSingleBusiness(@PathVariable int index) {
    Optional<Business> business = entryServer.getBusinessAtIndex(index);
    if (business.isPresent()) {
      return business.get();
    } else {
      return Collections.emptyList();
    }
  }
}
