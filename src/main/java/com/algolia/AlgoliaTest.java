package com.algolia;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.Index;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.objects.Query;
import com.algolia.search.responses.SearchResult;

public class AlgoliaTest {

  public static void main(String[] args) throws AlgoliaException {
    APIClient client = new ApacheAPIClientBuilder("APP_ID", "API_KEY").build();
    Index<Contact> index = client.initIndex("contacts", Contact.class);

    Contact contact = new Contact();
    contact.setFirstname("Jimmie");
    contact.setLastname("Barninger");
    contact.setFollowers(93);
    contact.setCompany("California Paint");

    index.addObject(contact).waitForCompletion();

    System.out.println(index.search(new Query("Jimmie")));

    SearchResult<Contact> search = index.search(new Query("Jimmie"));
    for (Contact hit : search.getHits()) {
      System.out.println(hit.getFirstname());
    }
  }
}

