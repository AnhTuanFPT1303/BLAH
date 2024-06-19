// emailValidation.js
function emailCheck() {
    var email = document.getElementById("email").value;
    var message = document.getElementById("emailWarning");
    message.innerHTML = "Invalid username part.<br>";

    // Part 1: Username validation
    var usernamePattern = /^(?!\.)([\w._-]*[^.])$/;
    var username = email.split('@')[0];
    if (!usernamePattern.test(username)) {
        message.innerHTML = "Invalid username part.<br>";
        return false;
    }

    // Part 2: Domain validation
    var domainPattern = /^\w+$/;
    var domain = email.split('@')[1].split('.')[0];
    if (!domainPattern.test(domain)) {
        message.innerHTML = "Invalid domain part.<br>";
        return false;
    }

    // Part 3: TLD validation
    var tldPattern = /^(\.\w+){1,2}$/;
    var tld = email.substring(email.indexOf(domain) + domain.length);
    if (!tldPattern.test(tld)) {
        message.innerHTML = "Invalid TLD part.<br>";
        return false;
    }

    // If all parts are valid
    message.innerHTML = '';
    return true;
}
