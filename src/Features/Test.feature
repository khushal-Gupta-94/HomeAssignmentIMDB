Feature: Home Assignment
  @Test
  Scenario: Verify IMBD home page details with individual movie page details
    Given Open Chrome Browser
    When Enter URL "https://www.imdb.com/chart/top/"
    Then Verify the site is opened successfully with title "IMDb Top 250 - IMDb"
    Then Fetch name, ratings, and movie release year of top 50 movies from IMDB site and verify that each and every movie link page is opened and all mentioned data is correct.
