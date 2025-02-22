CREATE TABLE tbl_event (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    pretty_name VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    start_date DATE,
    end_date DATE,
    start_time TIME,
    end_time TIME
);

INSERT INTO tbl_event (title, pretty_name, location, price, start_date, end_date, start_time, end_time)
VALUES
('CodeCraft Summit 2027', 'codecraft-2027', 'Online', 0.0, '2027-03-16', '2027-03-18', '19:00:00', '21:00:00'),
('Tech Innovation Expo', 'tech-expo-2027', 'São Paulo, Brasil', 150.0, '2027-06-10', '2027-06-12', '10:00:00', '18:00:00'),
('AI & Machine Learning Conference', 'ai-ml-conf-2027', 'Online', 49.99, '2027-09-05', '2027-09-06', '14:00:00', '20:00:00'),
('Startup Growth Bootcamp', 'startup-bootcamp-2027', 'Rio de Janeiro, Brasil', 299.0, '2027-07-22', '2027-07-25', '08:30:00', '17:30:00'),
('Cybersecurity Summit', 'cybersec-summit-2027', 'Online', 79.99, '2027-05-15', '2027-05-16', '09:00:00', '17:00:00'),
('Game Dev Conference', 'game-dev-conf-2027', 'Los Angeles, EUA', 199.0, '2027-10-10', '2027-10-13', '09:30:00', '18:30:00'),
('Cloud Computing Forum', 'cloud-forum-2027', 'Londres, Reino Unido', 129.99, '2027-11-20', '2027-11-22', '10:00:00', '17:00:00'),
('Blockchain & Crypto Summit', 'blockchain-crypto-2027', 'Dubai, Emirados Árabes', 350.0, '2027-08-12', '2027-08-14', '11:00:00', '19:00:00');