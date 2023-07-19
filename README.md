# java_react_website_search

# Summary
This is a coding challenge that I did for a company in July 2021. 
**The coding challenge allowed 6 hours to write code, I wrote this challenge in around 8 hours of coding after asking for more time. This was something like "TYPE AND DON'T LOOK BACK", so don't expect too much clear code here.**.

It was originally in 2 different repositories, now they are gathered in one repository here to make it easy to read.

Please go inside each subproject and read the proper README.md for more information.

## Backend
I used:
* SpringBoot
* Spring Data Rest:  This involves using special handlers
* InMemory H2 database

## FrontEnd
I used:
* React classes (I knew them pretty well at that time, instead of using hooks)
* No typescript (There was no time for type checking, or defining types)
* React Bootstrap 4



# Coding Challenge

The object of this coding challenge is to create an application so we can see your code-foo in action.
Please read these instructions carefully.

Things we're looking for:

* Navigable code.
* Efficient algorithms.
* Good separation of concerns.
* Error handling.
* Usage of libraries.
* Comments and documentation.

Things we like:

* Well-commented and well-organized code.
* Code quality over quantity.
* Small, meaningful, commits.
* Tests!
* Respect for the time limit.

## General Considerations

### Time Limit

The required **coding** time to invest in this project is at most **six** hours. Don't include any initial application setup or documentation in this time limit.

If you need (or want to put in) more time to complete or polish your deliverable, make sure to [keep us in the loop].

### Delivering Your Project

You have one week to deliver the project.

Once you’re done with your work, send us an email with the Github URL to your repository. After we review your application, we’ll schedule an interview to ask you some questions about it. Remember to share with us any secrets needed to run the application.

If you optionally want to host and run your project in a Cloud Platform of your choice (AWS, GCP, Cloud Foundry, etc.), send us the URL to your project.(#add-commit-push-repeat).

### Technology Stack

Your application should at least use:

* Java
* Spring Boot (preferably) or the Play framework.
* Tests for your code (use JUnit or the framework of your choice).
* A Database Management System (NoSQL or relational).
* HTML5 (using) and CSS.
* React (TypeScript is a plus, but you can use vanilla JavaScript).
* Job Queueing.

You can use any libraries, frameworks or complimentary programs you want (i.e. a Message Broker) to complete the challenge, except where explicitly mentioned.

### Add, Commit, Push, Repeat

You mst use Git to keep your code under version control, and push your code to a public Github repository where we can clone and review your project.

Commit your code frequently so we can see the evolution of your project. Make sure to add meaningful commit messages when committing code to your repository.

If you're putting more time on the project than required, please add the `[overtime]` string at the beginning of your commit messages, so we know this was done in _overtime_. For example, `git commit -m "[overtime] added machine learning engine"`.

## Application Requirements

We'd like you to create a simple expert directory search tool. The application should fulfill the following requirements:

* A member can be created using their name and a personal website address (use any public website URL as the personal website address for your tests).
* When a member is created, all the heading (H1 to H3) values are pulled in from the website to that members' profile.
* The website URL is shortened using [bit.ly](https://bit.ly).
* After the member has been added, a friendship can be defined with other existing members.
  * Friendships are bi-directional. For example, if Kilgore is a friend of Kurt, Kurt is always a friend of Kilgore as well.
* The interface should list all members with their name, shortened URL, and the number of friends.
* Viewing an actual member should display the name, website URL, shortened URL, a list of website headings, and links to their friends' pages.
* Now, looking at Kurt's profile, I want to find experts in the application who write about a certain topic and are not already friends with Kurt. Results should show the path of introduction from Kurt to the expert. For example, Kurt wants to get introduced to someone who writes about 'Trafalmadore'. Billy's website has a heading tag "Journeys To Trafalmadore". Eliott knows Kurt and Billy. An example search result would be Kurt ➡ Eliott ➡ Billy ("Journeys To Trafalmadore").

We encourage the use of any libraries for everything except for the search functionality, in which we want to see your simple algorithm approach.

### Add-ons

* Sign up/log in functionality.
* A UI that expands upon the basic requirements to have a user-friendly look and feel.
* Anything else you, as a user, would enjoy seeing in an interface like this.

## Final Considerations

* Use your imagination, creativity, best practices, etc. If it helps, imagine this app will land you a job!
* We need to see how well you know the tools, programming languages, and frameworks, so try to show off.
* Don’t be afraid to ask questions.

Good Luck!
