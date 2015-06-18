<#-- @ftlvariable name="employees" type="sk.stuba.fei.team.local.domain.Employee[]" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Administrácia zamestnanca</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/employee/add'/>" role="button">Vytvoriť
            zamestnanca</a>
    </div>
</div>

<h2 class="sub-header">Zoznam zamestnancov</h2>

<div class="row">
    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/employee/search'/>">
            <div class="form-group">
                <label for="text">Vyhľadanie:</label>
                <input type="text" name="text" class="form-control" id="text"
                       placeholder="Zadaj meno alebo prizevisko"
                       value="${search!""}" style="width: 400px;">
            </div>
            <input type="submit" value="Hľadaj" class="btn btn-default">
            <a class="btn btn-default" href="<@spring.url '/admin/employee/clear'/>">Zruš</a>
        </form>
    </div>
</div>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th>Prihlasovacie meno</th>
            <th>Meno zamestnanca</th>
            <th>Autorita</th>
            <th>Aktivovaný účet</th>
        </tr>
        </thead>
        <tbody>
            <#list employees as employee>
            <tr>
                <td>${employee_index + 1}</td>
                <td><a href="<@spring.url '/admin/employee/edit/' + employee.username />">${employee.username}</a></td>
                <td>
                ${employee.prefix_title!""} ${employee.firstName!""} ${employee.lastName!""}
                    <#if employee.suffix_title??><#if employee.suffix_title?length gt 0>${', ' + employee.suffix_title}</#if></#if>
                </td>
                <#if employee.getStringAuthorities()?seq_contains("USER")>
                    <td>USER</td>
                <#else>
                    <td>ADMIN</td>
                </#if>
                <td>
                    <#if employee.enabled!false>
                        <input type="checkbox" checked disabled>
                    <#else>
                        <input type="checkbox" disabled>
                    </#if>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@pt.dashboardPage>