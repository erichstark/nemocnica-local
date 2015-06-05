<#-- @ftlvariable name="offices" type="sk.stuba.fei.team.local.domain.Office[]" -->
<#-- @ftlvariable name="employee" type="sk.stuba.fei.team.local.domain.Employee" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Pridanie nového zamestnanca</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/employee'/>" role="button">Naspäť</a>
    </div>
</div>

<br>

<div class="table-responsive">
    <form name="patient" action="<@spring.url '/admin/employee/save'/>" method="post">
        <div class="form-group">
            <label for="employee-username">Prihlasovacie meno</label>
            <input type="text" name="username" class="form-control" id="employee-username"
                   placeholder="Prihlasovacie meno"
                   value="${employee.username!""}">
        </div>
        <div class="form-group">
            <label for="patient-password">Heslo</label>
            <input type="password" name="password" class="form-control" id="patient-password" placeholder="Heslo"
                   value="${employee.password!""}">
        </div>
        <div class="form-group">
            <label for="employee-prefix_title">Titul pred</label>
            <input type="text" name="prefix_title" class="form-control" id="employee-prefix_title"
                   placeholder="Titul pred"
                   value="${employee.prefix_title!""}">
        </div>
        <div class="form-group">
            <label for="employee-firstName">Meno</label>
            <input type="text" name="firstName" class="form-control" id="employee-firstName" placeholder="Meno"
                   value="${employee.firstName!""}">
        </div>
        <div class="form-group">
            <label for="employee-surname">Priezvisko</label>
            <input type="text" name="lastName" class="form-control" id="employee-surname" placeholder="Priezvisko"
                   value="${employee.lastName!""}">
        </div>
        <div class="form-group">
            <label for="employee-suffix_title">Titul za</label>
            <input type="text" name="suffix_title" class="form-control" id="employee-suffix_title"
                   placeholder="Titul za"
                   value="${employee.suffix_title!""}">
        </div>

        <table>
            <tr>
                <td style="width: 300px;">
                    <label class="radio-inline"><input type="radio" name="enabled" value="true" checked> Aktivovaný
                        účet</label>
                </td>
                <td>
                    <label class="radio-inline"><input type="radio" name="enabled" value="false"> Deaktivovaný
                        účet</label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonExpired" value="true" checked> Platný účet (non expired)
                    </label>
                </td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonExpired" value="false"> Neplatný účet
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonLocked" value="true" checked> Odomknutý účet (non
                        locked)
                    </label>
                </td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonLocked" value="false"> Uzamknutý účet
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="credentialsNonExpired" value="true" checked> Platné osobné údaje
                        (non expired)
                    </label>
                </td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="credentialsNonExpired" value="false"> Neplatné osobné údaje
                    </label>
                </td>
            </tr>
        </table>

        <br><br>

        <div class="form-group">
            <label for="patient-autority">Autorita</label>
            <select name="autority" class="form-control" id="patient-autority">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </div>

        <div class="form-group">
            <div>
                <input type="submit" value="Ulož" class="btn btn-success">
            </div>
        </div>

    </form>
</div>

</@pt.dashboardPage>