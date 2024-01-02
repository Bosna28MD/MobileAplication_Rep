<?php

if( !empty($_POST['email']) && !empty($_POST['pwd']) ){

    $email=$_POST['email'];
    $pwd=$_POST['pwd'];
    $result=array();
    $con=mysqli_connect("localhost","root","b9f4648028100%d","mobileandroid_db1");

        if($con){

            $sql_query_select="SELECT * FROM users_tabel where email='".$email."'";
            $res=mysqli_query($con,$sql_query_select);
            if(mysqli_num_rows($res)!=0 ){
                $row=mysqli_fetch_assoc($res);
                if(password_verify($pwd,$row['password']) ){
                    $result=array("status"=>"success","message"=>"Login Successful","name"=>$row['name'],"email"=>$row['email'],"date"=>$row['birthday'],
                    "id"=>$row['id']);
                }
                else{
                    $result=array("status"=>"failed","message"=>"Login Failed Incorrect Password");
                }
            }else{
                $result=array("status"=>"failed","message"=>"Login Failed Email not registered");
            }

        }else{
            $result=array("status"=>"failed","message"=>"Connection Failed");
        }

}else{
    $result=array("status"=>"failed","message"=>"All fields required to be completed");
}

echo json_encode($result,JSON_PRETTY_PRINT);


?>