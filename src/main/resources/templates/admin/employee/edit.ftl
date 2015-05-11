<#-- @ftlvariable name="offices" type="sk.stuba.fei.team.local.domain.Office[]" -->
<#-- @ftlvariable name="employee" type="sk.stuba.fei.team.local.domain.Employee" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.dashboardPage>
<h1 class="page-header">Editácia zamestnanca</h1>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/employee'/>" role="button">Naspäť</a>
    </div>
</div>

<br>

<div class="table-responsive">
    <form name="patient" action="<@spring.url '/admin/employee/save'/>" method="post">
        <div class="form-group" style="display: none;">
            <label for="employee-username">Prihlasovacie meno</label>
            <input type="text" name="username" class="form-control" id="employee-id" placeholder="Prihlasovacie meno"
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
                    <label class="radio-inline"><input type="radio" name="enabled" value="true"
                                                       <#if  employee.enabled>checked</#if> > Aktivovaný účet</label>
                </td>
                <td>
                    <label class="radio-inline"><input type="radio" name="enabled" value="false"
                                                       <#if !employee.enabled>checked</#if> > Deaktivovaný účet</label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonExpired" value="true"
                               <#if  employee.accountNonExpired>checked</#if> > Platný účet (non expired)
                    </label>
                </td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonExpired" value="false"
                               <#if !employee.accountNonExpired>checked</#if> > Neplatný účet
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonLocked" value="true"
                               <#if  employee.accountNonLocked>checked</#if> > Odomknutý účet (non locked)
                    </label>
                </td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="accountNonLocked" value="false"
                               <#if !employee.accountNonLocked>checked</#if> > Uzamknutý účet
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="credentialsNonExpired" value="true"
                               <#if  employee.credentialsNonExpired>checked</#if> > Platné osobné údaje (non expired)
                    </label>
                </td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="credentialsNonExpired" value="false"
                               <#if !employee.credentialsNonExpired>checked</#if> > Neplatné osobné údaje
                    </label>
                </td>
            </tr>
        </table>

        <div class="form-group">
            <label for="patient-autority">Autorita</label>
            <select name="autority" class="form-control" id="patient-autority">
                <#if employee.getStringAuthorities()?seq_contains("USER")>
                    <option value="USER" selected="selected">USER</option>
                    <option value="ADMIN">ADMIN</option>
                <#else>
                    <option value="USER">USER</option>
                    <option value="ADMIN" selected="selected">ADMIN</option>
                </#if>
            </select>
        </div>

        <div class="form-group">
            <div>
                <input type="submit" value="Ulož" class="btn btn-success">
            </div>
        </div>

    </form>
</div>

<br>

<div class="row">
    <h2>Špecializácia</h2>
    <hr>

    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/employee/specialization/add'/>">
            <div class="form-group">
                <label for="employee-office">Specializácia:</label>
                <input type="text" class="form-control" name="specialization" value="">
                <input type="hidden" name="username" value="${employee.username}">
            </div>
            <input type="submit" value="Pridaj" class="btn btn-success">
        </form>
    </div>

    <div class="col-md-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th>Názov špecializácie</th>
                <th style="width: 60px;">Akcia</th>
            </tr>
            </thead>
            <tbody>
                <#if employee.specializations??>
                    <#list employee.specializations as specialization>
                    <tr>
                        <td>${specialization_index + 1}</td>
                        <td>${specialization}</td>
                        <td>
                            <a href="<@spring.url '/admin/employee/'+employee.username+'/specialization/'+specialization_index+'/delete' />"
                               onclick="return confirm('Naozaj?');">Zmazať</a></td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <h2>Zamestnanec - ambulancie</h2>
    <hr>

    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/employee/office/add'/>">
            <div class="form-group">
                <label for="employee-office">Pridanie ambulancie:</label>
                <select name="id_office" class="form-control" id="employee-office">
                    <#list offices as office>
                        <option value="${office.id}">${office.name}</option>
                    </#list>
                </select>
                <input type="hidden" name="username" value="${employee.username}">
            </div>
            <input type="submit" value="Pridaj" class="btn btn-success">
        </form>
    </div>

    <div class="col-md-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th style="width: 60px;">ID</th>
                <th>Názov ambulancie</th>
                <th style="width: 60px;">Akcia</th>
            </tr>
            </thead>
            <tbody>
                <#if employee.offices??>
                    <#list employee.offices as office>
                    <tr>
                        <td>${office_index + 1}</td>
                        <td>${office.id}</td>
                        <td>${office.name}</td>
                        <td>
                            <a href="<@spring.url '/admin/employee/'+employee.username+'/office/'+office.id+'/delete' />"
                               onclick="return confirm('Naozaj?');">Zmazať</a></td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
</div>

</@pt.dashboardPage>