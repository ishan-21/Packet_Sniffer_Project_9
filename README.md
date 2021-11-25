# Project_Sniffer_Project_9
This repository contains the files for the Project 9 of OOP course :Project Sniffer
Introduction to the Project

Name 1: Ishan Rai ID Number: 2019B3A70504P
Name 2: Shrey Bansal ID Number: 2019B3A70592P

Here is a general overview of the different classes of which the project is composed:-

Abstract Traffic Class
Attack Traffic Class
Normal Traffic Class
Victim Host Class

The goal of the Project - Sniff all the packets on a port, and extract all the information from the packets including source IP, destination IP, source port, destination port. If the number of packets exceeds 1000, then it is classified as an attack through the victim host class.

Normal Traffic Class

The Normal Traffic class is one of the subclasses of the traffic class, through this class we generate traffic towards the Victim Host. The total number of packets through this class = 5. For showing that data travels in the packet, we have added a string in each of the packets which can be imputed to the packet through the console and is visible on the console of the Victim Host class along with all the other info such as source port, destination port, etc.

Attack Traffic Class 

Attack Traffic class is one of the subclasses of the traffic class, through this class we generate traffic towards the Victim Host. Total number of packets through this class = 1050. For showing that data travels in the packet, we have added a string in each of the packets which are randomly generated through a random String Generator function and are visible on the console of the Victim Host class along with all the other info such as source port, destination port, etc.

Victim Host Class 

This is the class where the port scanning is done and it presents the required data along with the input string to the console. As soon as the number of packets exceeds 1000, with each new package, a message of An Attack is Happening is printed to the string. It not only accepts the packets but also scans the packet, which is done through the method scan port.
