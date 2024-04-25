<?php
            if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                $id= $_POST['classid'];
                $topic= $_POST['topic'];
                $duration= $_POST['duration'];
                $zlink= $_POST['zlink'];
                include "dbphpcon.php";
                $insertData = "INSERT INTO `addclass` (`classid`,`topic`,`duration`,`zlink`) VALUES ('$id','$topic','$duration','$zlink');";
                $result = mysqli_query($conn, $insertData);

                if ($result) {
                    echo '<div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Success!</strong> You have been a part of Rehbar Foundation.
              </div>';
                } else {
                    echo '<div class="alert alert-error alert-dismissible fade show" role="alert">
                <strong>Success!</strong> You have been a part of Rehbar Foundation.
              </div>';
                }
            }
            ?>