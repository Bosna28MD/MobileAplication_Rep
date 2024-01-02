<?php

if( !empty($_POST['name']) && !empty($_POST['email']) &&  !empty($_POST['birthday']) &&  !empty($_POST['pwd']) ){
    $con=mysqli_connect("localhost","root","b9f4648028100%d","mobileandroid_db1");
    if($con){

        $name=$_POST['name'];
        $email=$_POST['email'];
        $birthday=$_POST['birthday'];
        $pwd=password_hash($_POST['pwd'],PASSWORD_DEFAULT); #encrypt password
        
        

        $sql_query_select="SELECT * FROM users_tabel where email='".$email."'";
        $res=mysqli_query($con,$sql_query_select);
        if(mysqli_num_rows($res)==0 ){
            $sql_query_insert="insert into users_tabel(email,name,birthday,password) values('".$email."','".$name."','".$birthday."','".$pwd."') ";
            if(mysqli_query($con,$sql_query_insert) ){
                $sql_select1="SELECT * FROM users_tabel where email!='".$email."'";
                $res2=mysqli_query($con,$sql_select1);
                
                if(mysqli_num_rows($res2)>0 ){
                    $sql_select_id="SELECT * FROM users_tabel where email='".$email."'";
                    $res_id=mysqli_query($con,$sql_select_id);
                    $row_id=mysqli_fetch_assoc($res_id);

                    while($row2=mysqli_fetch_assoc($res2)){
                        $insert_friend_tabel="insert into friend_tabel(id_user1,id_user2) values(".$row_id["id"].",".$row2["id"].");";
                        #mysqli_query($con,$insert_friend_tabel);
                        if(mysqli_query($con,$insert_friend_tabel)){
                            continue;
                        }
                    }

                }

                echo "Success Registration";
            }else{
                echo "Registration Failed";
            }
        }else{
            echo "This email is already registered";
        }

    }else{
        echo "Database Connection Failed";
    }

}else{
    echo "All fields required to be completed";
}


?>