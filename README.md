![image](https://user-images.githubusercontent.com/92807364/205335090-8cf80e56-95c5-4bc7-8bfa-61a3443e8dcc.png)
# Extract information about a company from its domain easily!

With the help of this application, you can find the following information about company in seconds:
* Company name
* Twitter URL
* Facebook URL
* Company logo
* Company name
* Company icon
* Number of employees
* Company address

# Design of code

Let's start from the very beginning - <b>DataController class</b>. As a user, first thing you would do is enter the company you want to have information about. And, basically, at this moment there are two possible scenarious: the domain is in our database or not. If it is, we happily display you the information about company in json format. If not - here the magic beginns.

For the database, we used Singleton pattern to ensure that there is only one unique database. Actually, it is a great example of the Singleton pattern usage. Regardless of who wants to access data, the database of companies would be common. The same pattern was used for <b>SearchEngine</b> class. <b>getRequest(inputDomain)</b> parses the data, so that as outcome we have information about company based on link given. Also, it checks if the link is already in database. In the opposite scenario, we call method of <b>searchEngine</b> class <b>searchInfoAboutCompany(inputDomain.getName())</b>. 

After that, we initialize object of <b>DomainData</b> class with lombok's @NoArgsConstructor. We fed there only the domain field. Now it would be optimal to use pattern Chain of Responsibility. We have four main strategies to fill out all the information:
* PDL Strategy
* Regex Strategy
* Brandfetch Strategy
* GooglePlace Strategy

First, we start PDL Strategy. If it finds all the required data - perfect. Else, it continues the process, going straight to the Regex Strategy and Brandfetch Strategy. If we still did not found the address, the GooglePlace Strategy comes in handy. All in all, it looks like this: PDL -> Regex -> Brandfetch -> GooglePlace. Unfortunately, if some kind of information does not exist, the program fills out the value of field as "No information about this field".
