<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Inštalácia systému</#assign>
<@pt.genericPage pageTitle=pageTitle>
<div class="container">
    <h1>${pageTitle}</h1>

    <div id="alertContainer">
    </div>
    <form id="facility-setup" name="facility">
        <h2>Krok 1 <span class="text-muted">>>Krok 2 >> Krok 3</span></h2>

        <h3>Údaje o zariadení</h3>
        <span class="text-muted" style="padding-bottom: 10px">* Povinné údaje</span>

        <div class="form-group">
            <label for="name">Názov*</label>
            <input type="text" name="name" class="form-control" id="name" required>
        </div>
        <div class="form-group">
            <label for="streetAndNumber">Ulica a číslo</label>
            <input type="text" name="streetAndNumber" class="form-control" id="streetAndNumber">
        </div>
        <div class="form-group">
            <label for="zip">PSČ</label>
            <input type="text" name="zip" class="form-control" id="zip">
        </div>
        <div class="form-group">
            <label for="city">Mesto</label>
            <input type="text" name="city" class="form-control" id="city">
        </div>

        <h3>Parametre synchronizácie s Globálnym serverom</h3>

        <div class="form-group">
            <label for="clientID">Client ID*</label>
            <input type="text" name="clientID" class="form-control" id="clientID" required>
        </div>
        <div class="form-group">
            <label for="clientSecret">Client Secret*</label>
            <input type="text" name="clientSecret" class="form-control" id="clientSecret" required>
        </div>
        <div class="form-group">
            <label for="username">Meno*</label>
            <input type="text" name="username" class="form-control" id="username" required>
        </div>
        <div class="form-group">
            <label for="password">Heslo*</label>
            <input type="text" name="password" class="form-control" id="password" required>
        </div>
        <div class="form-group">
            <button type="button" id="setupFacility" class="btn btn-success ladda-button" data-style="zoom-in"
                    onclick="registerFacility()">
            <span class="ladda-label">
                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                Pokračovať
            </span>
            </button>
        </div>
    </form>
    <div id="admin">
        <form>
            <h2>Krok 1 >> Krok 2 <span class="text-muted">>> Krok 3</span></h2>

            <h3>Vytvorenie administrátorského konta</h3>
            <span class="text-muted" style="padding-bottom: 10px">* Povinné údaje</span>

            <div class="form-group">
                <label for="admin-name">Prihlasovacie meno*</label>
                <input type="text" name="admin-name" class="form-control" id="admin-name" required>
            </div>
            <div class="form-group">
                <label for="admin-password">Heslo*</label>
                <input type="password" name="admin-password" class="form-control" id="admin-password" required>
            </div>
            <span class="text-muted" style="padding-bottom: 10px">Ostatné údaje je možné nastaviť neskôr.</span>
            <button id="createAdmin" type="button" class="btn btn-success ladda-button" data-style="zoom-in"
                    onclick="registerAdmin()">
            <span class="ladda-label">
                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                Pokračovať
            </span>
            </button>
        </form>
    </div>
    <div id="success">
        <h2>Krok 1 >> Krok 2 >> Krok 3</h2>

        <h3>Hotovo!</h3>

        <div style="padding-top: 40px" class="form-group">
            Pre dokončenie inštalácie je nutné sa odhlásiť a následne prihlásiť na vytvorené administrátorské konto.
            <a href="<@spring.url '/logout'/>" class="btn btn-default btn-lg">
                <span class="glyphicon glyphicon-home"></span> Odhlásiť
            </a>
        </div>
    </div>
</div>
</@pt.genericPage>