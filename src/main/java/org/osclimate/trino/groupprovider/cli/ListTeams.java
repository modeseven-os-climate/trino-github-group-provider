package org.osclimate.trino.groupprovider.cli;

import org.osclimate.trino.groupprovider.github.model.Team;
import org.osclimate.trino.groupprovider.github.GitHubApiClient;
import org.osclimate.trino.groupprovider.github.GitHubService;

import java.util.List;

class ListTeams {
    public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	    System.out.printf("usage: ListTeams <github-org-name>\n");
	    System.exit(1);
	}
	String org = args[0];
	String apiToken = System.getenv("GITHUB_API_TOKEN");
        if (apiToken == null) {
	    System.out.printf("environment variable GITHUB_API_TOKEN must be set\n");
	    System.exit(1);
	}
	System.out.printf("teams for github org %s\n", org);
        GitHubService api = GitHubApiClient.getClient(GitHubService.class, "https://api.github.com");
	retrofit2.Response<List<Team>> response = api.listTeams("Bearer " + apiToken, org, 1000, 1).execute();
        List<Team> teams = response.body();
	for (Team t : teams) {
	    System.out.printf("team: %s\n", t.slug);
	}
    }
}