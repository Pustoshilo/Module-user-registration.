# Module-user-registration.
Module user registration.  demonstration project, allows you to record, monitor and edit users

User Attributes: email, password, role.
When user authentication is used "random salt", which is stored in the database and "local salt" of the application.

link users table to a table of roles implemented through hibernate-annotations @ManyToOne,

used 2-level-cache (ehcache).

As the controller is used spring mvc, ideas manager - Tiles, uses automatic form validation.

UI-interface is done in a simple, solely for demonstration. Use JSTL, SpEL. Implemented pagination.

Database: MySQL, see the dump file.
