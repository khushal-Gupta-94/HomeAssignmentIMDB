$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/Features/Test.feature");
formatter.feature({
  "name": "Home Assignment",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Verify IMBD home page details with individual movie page details",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.step({
  "name": "Open Chrome Browser",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.TestIMBD.open_Chrome_Browser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter URL \"https://www.imdb.com/chart/top/\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.TestIMBD.enter_URL(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Verify the site is opened successfully with title \"IMDb Top 250 - IMDb\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.TestIMBD.verifyIMBDsite(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Fetch name, ratings, and movie release year of top 50 movies from IMDB site and verify that each and every movie link page is opened and all mentioned data is correct.",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.TestIMBD.fetchdata()"
});
formatter.result({
  "error_message": "java.lang.AssertionError: The following asserts failed:\n\tRating is not matched. expected [93] but found [92],\n\tRating is not matched. expected [92] but found [91],\n\tRating is not matched. expected [89] but found [88],\n\tRating is not matched. expected [88] but found [87],\n\tRating is not matched. expected [87] but found [86],\n\tRating is not matched. expected [87] but found [86],\n\tRating is not matched. expected [87] but found [86],\n\tRating is not matched. expected [86] but found [85],\n\tRating is not matched. expected [86] but found [85],\n\tRating is not matched. expected [86] but found [85],\n\tRating is not matched. expected [86] but found [85],\n\tRating is not matched. expected [86] but found [85],\n\tRating is not matched. expected [88] but found [85],\n\tRating is not matched. expected [87] but found [85],\n\tRating is not matched. expected [85] but found [84]\r\n\tat org.testng.asserts.SoftAssert.assertAll(SoftAssert.java:47)\r\n\tat org.testng.asserts.SoftAssert.assertAll(SoftAssert.java:31)\r\n\tat stepDefinitions.TestIMBD.fetchdata(TestIMBD.java:119)\r\n\tat ✽.Fetch name, ratings, and movie release year of top 50 movies from IMDB site and verify that each and every movie link page is opened and all mentioned data is correct.(file:///G:/Demo/./src/Features/Test.feature:7)\r\n",
  "status": "failed"
});
});