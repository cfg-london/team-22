# NobelBook
## _The facebook for Nobel Laureates._
## [Presentation](https://docs.google.com/presentation/d/1TCnt7IWUaFaK1b0TLFharIR84SaM8LKgozB_--wMTno/edit?usp=sharing)
---
## The Goal
Our objective is to _improve the retainability of the Nobel Prize website_. We solved this through two means: firstly, improving the accessibility 
of the website for existing users. We aimed to achieve this by using a familiar layout akin to that of facebook for making an accessible and 
**interesting visualization**. The second solution is creating **new links across Laureates**. This is done by creating a metric for measuring the similarity
in two laureate's speech writing. 

## The Process
1. Scraping of [nobelprize.org](https://www.nobelprize.org/). This data gets set as a set of text files to the _data mining section_ and the _backend_.
2. **The Backend**:
    1. Parses through data
    2. Provides endpoints for the front-end to interact with the resources
3. **Data Mining**:
    1. Analyzes the data
    2. Finds new edges that can possible connect to Laureates together.
    3. Ultimately sends the links of an individual to the backend.
4. The front-end receives the resources from the backend and displays it in a Facebook-esque style.

## Running the App
1. In the `Scraper` subsection, you can find the Java and Python code for parsing the nobleprize html pages.
2. When those text files are extracted, the Java backend runs.
3. `cd ./backend` and run the `RestClient.java`
4. The `Emotion.java` file is used for finding similarities among a set of laureates.
5. `cd ./newFrontEnd` holds the current files and is what is hosted by the backEnd.
