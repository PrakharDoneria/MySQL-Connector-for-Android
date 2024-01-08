<?php

// Set up MySQL connection parameters
$servername = "put your server name here";
$username = "your username with which you made DB";
$password = "your DB password";
$database = "Database used to store tables";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Handle POST request (add user)
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Retrieve data from the request
    $email = $_POST["email"];
    $password = $_POST["password"];

    // Perform SQL query to insert data into the "auth" table
    $sql = "INSERT INTO auth (email, password) VALUES ('$email', '$password')";

    if ($conn->query($sql) === TRUE) {
        // Successfully added user
        echo "User added successfully";
    } else {
        // Failed to add user
        echo "Error: " . $conn->error;
    }
}

// Handle GET request (check user)
if ($_SERVER["REQUEST_METHOD"] === "GET") {
    // Retrieve data from the request
    $email = $_GET["email"];
    $password = $_GET["password"];

    // Perform SQL query to check if the user exists in the "auth" table
    $sql = "SELECT * FROM auth WHERE email = '$email' AND password = '$password'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // User exists
        echo json_encode(array("exists" => true));
    } else {
        // User does not exist
        echo json_encode(array("exists" => false));
    }
}

// Close the database connection
$conn->close();

?>
