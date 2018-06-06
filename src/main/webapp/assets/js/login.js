(function(){
    window.addEventListener("load", function(){
        const loginButton = document.getElementById('login-button');
        if(loginButton !== null){
            loginButton.addEventListener('click', (event) =>{
                event.preventDefault();

                const passwordInput = document.getElementById("password").value;
                document.getElementById("password-e").value = CryptoJS.SHA3(passwordInput);
                document.getElementById("login-form").submit();
            });
        }
    })
}());