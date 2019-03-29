Metronome
======

##Project for Code Louisville Android Spring 2019

I am constantly frustrated by how complicated most metronome apps are, how long they take to load, and that their color scheme is much to bright to use in a dark gig. This app is written to address these issues.

This app is written in Kotlin, and uses Room to manage a database. There is currently no click in the released version because I was having problems getting the beats even, and didn't want to risk the other aspects of the project while I worked on that.

##Requirements for Personal Project

**User Interaction**
    Add new tempos
    Scroll through previously used tempos
    Travel back and forth between selection and tempo page

**Two Activities**
    MainActivity where tempo is selected
    TempoActivity where tempo is viewed (placeholder for click)

**Interact with Local Database/API**  (Chose Local Database)
    Create - adds set of pre-selected tempos and user-selected tempos to Room Database
    Read - reads all tempos in database into recycler view on main window
    Update - a bit more subtle, this tracks how many times a given tempo is selected and moves them to the top of the list.
    Delete (optional)

**Code Has Comments**
    Yes

**README File**
    Brief Description
        Not as brief as hoped for, unfortunately.
    Instructions
        Please don't break it. Afterward, enjoy a Reese's. :-)


**Project Code in Github**
Look! We're in Github!

