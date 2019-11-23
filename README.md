# Project 4

The Museum of Modern Art published a dataset containing more than 130k artworks from its catalog, joining the UK’s Tate, the Smithsonian’s Cooper Hewitt, and other forward-thinking museums. The MoMA data contains the names of the artwork and artist, the dates created and acquired, and the medium — but no images. This data file can be found in its raw form at the  [Museum of Modern Art github page](https://github.com/MuseumofModernArt/collection) and is revised frequently. However, you should NOT use this file - instead, use the simplified file that has been uploaded to Blackboard.

<!-- MarkdownTOC -->

- The Data
- The Goal
- Teams
- Step 1 - Import and Clone the Repo.
- Step 2 - Download the Data
- Step 3 - Have a Plan
- Step 4 - Write the code for Class\(es\) to store the data for each Artist and Artwork
- Step 5 - Write code to Parse the Data File
- Step 6 - Write the code to count frequency of the tokens in each Artwork
- Step 7 - Write the code to count the tokens for each Artist
- Step 8 - Write the code to compare Artists.
- Step 9 - Write the code to print the results.
- Step 10 - Push to Github
- Grading
- Extra Credit
- Other data sets

<!-- /MarkdownTOC -->


## The Data
You are being provided with a link to a data set of 138124 artwork descriptions collected from the MoMa dataset for all artwork in the museum collection. This is a comma-separated value file (.csv) in which all records are surrounded by quotation marks. You can open up the file in any text editor (e.g., Sublime) or even Excel if you want to look at the data directly (and you should always inspect your data!).

## The Goal
For this project you will be designing an application containing one main class: `ArtistAnalyzer` and a few supporting classes. The exact contents of these classes are largely up to you, though there is functionality that is required. Some guidelines are given below for how to structure your code, but a major component of this assignment is to design classes and methods that achieve the desired results. At minimum, it is required that your application should start when the ArtistAnalyzer class is launched. It should take one or more command line arguments, and depending on what is passed in the command line, it should output an analysis of the file.

Detailed class designs and recommendations are described below.

# Teams
You may partner with another student on this project to discuss code and strategies for completing the various parts of the assignment. You may share code with your partner, but you should still write much of your own code in your own repository. You should still update your repository (by pushing) incrementally as you make progress. It is up to teammates to ensure that their partner adheres to the <a href="https://www.american.edu/academics/integrity/code.cfm">American University Honor Code.</a> You may use [pair programming](https://en.wikipedia.org/wiki/Pair_programming), however, you must each take turns in the driver role on your own laptop. I should see commits and a submission on Github for each of you to get full credit for this assignment. If you decide to work with a partner, modify the statement below:

- [ ] I worked with [insert partner name] on this project.
- [x] I worked on my own.

## Step 1 - Import and Clone the Repo.
1. Clone the repository to your local machine.
1. As a reference for how to use git, I suggest <a href='http://codingdomain.com/git/'>this site</a>, as it avoids some of the more complicated theory behind git and focuses on the bare minimum practicalities.

## Step 2 - Download the Data
This is a big data file, so it is not being distributed with the repo for this project. A simplified version of the MoMa data has been prepared for you, and is available on Blackboard. You need to download the file labeled "Artwork Simplified". For this project, .csv files have been added to the .gitignore file, so they should not be pushed to your shared repo.

## Step 3 - Have a Plan
No matter what methods and fields are in your classes, you need to be able to ensure that you have a plan for the overall design. We have talked in class about UML diagrams, and you should be quite comfortable at this stage documenting what should be contained in each of the classes you will create. As with the previous project, you can use an online editor, text file, or diagramming tool (e.g., even Powerpoint). Unlike the previous project, this does not have to be a full UML diagram - even a short written description of what you intend will suffice for this project. I **strongly** recommend you employ top-down, bottom-up, or some combination of the two design strategies for this project.

## Step 4 - Write the code for Class(es) to store the data for each Artist and Artwork
Your first priority should be to write the code that defines the fields and methods in those classes. While you have some flexibility in how you define things, the class must store and provide access to the important contents of the advertisements for each row (i.e., `artist` and `title`).

Some important considerations:
1. The way that data is stored in objects of this class **heavily** influences the performance of the search. If you keep the description as a String, you absolutely can determine if the description contains a search term. However, it will be slow.
1. You must demonstrate the use of a Map OR a Set in either of your classes.

## Step 5 - Write code to Parse the Data File
Now that you have a way to store data in objects, your next task is to write code to read the data into the program from the .csv file. You have already done this for a previous project, so you should be quite comfortable at this stage parsing a .csv file. The most important fields to include are `title` and `artist`, though you may include other fields if you wish.

Some important considerations:

1. Where will the code for this go? It could, potentially, be in any of the classes.
1. Is it static or non-static?
1. Does it go into a main method, or some other method? Will there even be a main method in your program?
1. Artworks sometimes have multiple artists separated by a comma. Be sure to handle these appropriately, and attribute the tokens to the correct artist. "Yoko Ono, John Lennon" is two artists, not one, and there should be separate objects for each of them.

Regardless of your choices, all of the data should be converted into objects and made available in your program for use by the `ArtistAnalyzer` application.

## Step 6 - Write the code to count frequency of the tokens in each Artwork
Each artwork has a title, which consists of one or more words or numbers. Sometimes, words recur more frequently. For each artwork, you should count the occurrence of each of the words and store it in a data structure belonging to the artwork object. Many titles are short, such as "Odol" by Stuart Davis, and only contain a single token.

> {"odol":1}

Others can be longer and have repetition, such as "Suprematist Composition: White on White" Kazimir Malevich:

> {"suprematist":1 , "composition":1", "white":2, "on":1}

## Step 7 - Write the code to count the tokens for each Artist
Most artists are associated with multiple artworks, and use different word types to describe what they have created. For each artist, count the tokens that they use when titling the artworks that they have created. Some authors use nondescript words, like "untitled" or numbers. Others employ a rich vocabulary, using many different words.

## Step 8 - Write the code to compare Artists.
When your ArtistAnalyzer class is run from the command line, it should take three possible sets of arguments:

Num Arguments | Example | Description
-|-|-
2 | `java ArtistAnalyzer "Andy Warhol" "Carroll Dunham"` | Compare the tokens for the two artists and return a numerical representation of the similarity of the words that they use.
1 | `java ArtistAnalyzer "Yoko Ono"` | For the named artist, find the artist that is most similar.
0 | `java ArtistAnalyzer` | Find the pair of artists that are the most similar of all artists.

How do you compute similarity? There are a number of approaches. The simplest is to count the number of words that are present in both artist token collections. A better indicator would incorporate not only the count of matching words, but also the number of occurrences for each word.

## Step 9 - Write the code to print the results.
For the results obtained by the above analysis, provide a pretty formatted results table indicating WHY the artists are similar. What words do they both use? What words are only used by one, but not the other?

## Step 10 - Push to Github
Do not forget to push your final submission to Github before the deadline.

# Grading
Grading will be assigned according to the following categories. As described in the syllabus, each category can receive a  &#10003; (satisfactory); a &#10003;+ (above and beyond); a &#10003;- (incorrect, incomplete, or sloppy); or in the worst case an &#10005;, meaning it was incorrect in several ways. A category assigned an &#10005; can carry significant grade penalties for this assignment, but usually does not receive more than 50% of the possible credit for an task.

Missing components receive a score of zero, but it is better to be missing a component (or comment it out) instead of submitting code that does not compile. Code that does not compile will not be graded, and a zero will be assigned for the project. Submitted code that contains runtime errors will be graded at the instructor's discretion.

1. **5%** Multiple commits were made throughout the project as progress was made, not just one big upload at the end. Multiple branches were created throughout the course of the project and were merged as progress was made on individual features.
1. **5%** The code is easy to follow and understand, and a plan is included.
1. **10%** The data file is parsed correctly and made available to the other classes in some way.
1. **20%** A class is created to store and organize the data for each artist and artwork.
1. **15%** The tokens are counted correctly for each Artwork.
1. **15%** The tokens are counted correctly for each Artist.
1. **20%** Depending on the command line arguments, the ArtistAnalyzer computes the appropriate results.
1. **10%** The results of analysis are printed neatly to the screen.

# Extra Credit
There are two extra credit opportunities for this assignment that were described in class. You are free to try either, or both, depending on your interests. These will be graded using the same  &#10003; (satisfactory); a &#10003;+ (above and beyond); a &#10003;- (incorrect, incomplete, or sloppy); or &#10005; rubric. Attempting these cannot hurt your grade, as long as everything compiles. However, even if you successfully complete these, your homework grade for the semester *cannot* be greater than 100%.
1. **+20%** Replace the similarity method with a calculation based on term-frequency-inverse-document-frequency [(tf-idf)](https://en.wikipedia.org/wiki/Tf%E2%80%93idf). This is recommended for students who are more interested in digging into algorithms, data science, and machine learning.
1. **+20%** Instead of calling the functions from the command line, implement the UI described in class with JavaFX. This is recommended for students who are more interested in GUI design, human-computer interaction, and data visualization.

## Other data sets
If this kind of thing interests you, here is some additional reading material and data sets to explore:
- https://github.com/MuseumofModernArt/collection
- https://github.com/tategallery/collection
- https://github.com/cooperhewitt/collection
- http://www.penn.museum/collections/data.php
- https://www.rijksmuseum.nl/en/api
- https://www.brooklynmuseum.org/opencollection/api/
- https://medium.com/@blprnt/a-sort-of-joy-1d9d5ff02ac9
- https://www.imls.gov/research-evaluation/data-collection/museum-universe-data-file