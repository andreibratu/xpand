DROP TABLE IF EXISTS crew_members;

CREATE TABLE crew_members(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    isRobot BOOLEAN NOT NULL,
    crewId INT NOT NULL
);