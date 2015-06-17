<#-- @ftlvariable name="office" type="sk.stuba.fei.team.local.domain.Office" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Editácia ambulančných hodín</#assign>
<@pt.dashboardPage pageTitle=pageTitle>

<div class="row">
    <div class="col-md-1">
        <a class="btn btn-info" href="<@spring.url '/admin/office/edit/${office.id}'/>" role="button">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            Späť
        </a>
    </div>
</div

<div class="panel-body" id="form">
    <div class="form-group">
        <h3>Ordináčné hodiny</h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Deň</th>
                <th>Ráno od - do</th>
                <th>Cez deň od - do</th>
            </tr>
            </thead>
            <tbody>
                <#list office.hours?sort_by("date") as hour>
                <tr>
                    <input type="hidden" name="${hour_index}-date" value="${hour.date}">
                    <input type="hidden" name="${hour_index}-id" value="${hour.id}">
                    <td><#if hour.date == 1>Pondelok</#if><#if hour.date == 2>Utorok</#if><#if hour.date == 3>
                        Streda</#if><#if hour.date == 4>Štvrtok</#if><#if hour.date == 5>Piatok</#if></td>
                    <td>
                        <select name="${hour_index}-reservationMorningFrom">
                            <#assign hours = [5,5,6,6,7,7,8,8,9,9,10,10]>
                            <#list hours as h>
                                <#assign m = h * 60>
                                <#if h_index % 2 == 0>
                                    <option value="${m}" <#if m==hour.reservationMorningFrom!0>selected</#if>><#if h<10>
                                        0</#if>${h}:00
                                    </option>
                                <#else>
                                    <option value="${m+30}"
                                            <#if m+30==hour.reservationMorningFrom!0>selected</#if>><#if h<10>
                                        0</#if>${h}:30
                                    </option>
                                </#if>
                            </#list>
                        </select>
                        &nbsp;-&nbsp;
                        <select name="${hour_index}-reservationMorningTo">
                            <#assign hours = [8,8,9,9,10,10,11,11,12,12,13,13]>
                            <#list hours as h>
                                <#assign m = h * 60>
                                <#if h_index % 2 == 0>
                                    <option value="${m}" <#if m==hour.reservationMorningTo!0>selected</#if>><#if h<10>
                                        0</#if>${h}:00
                                    </option>
                                <#else>
                                    <option value="${m+30}"
                                            <#if m+30==hour.reservationMorningTo!0>selected</#if>><#if h<10>0</#if>${h}
                                        :30
                                    </option>
                                </#if>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <select name="${hour_index}-reservationFrom">
                            <#assign hours = [8,8,9,9,10,10,11,11,12,12,13,13]>
                            <#list hours as h>
                                <#assign m = h * 60>
                                <#if h_index % 2 == 0>
                                    <option value="${m}" <#if m==hour.reservationFrom!0>selected</#if>><#if h<10>
                                        0</#if>${h}:00
                                    </option>
                                <#else>
                                    <option value="${m+30}" <#if m+30==hour.reservationFrom!0>selected</#if>><#if h<10>
                                        0</#if>${h}:30
                                    </option>
                                </#if>
                            </#list>
                        </select>
                        &nbsp;-&nbsp;
                        <select name="${hour_index}-reservationTo">
                            <#assign hours = [13,13,14,15,16,17,18,18,19,19]>
                            <#list hours as h>
                                <#assign m = h * 60>
                                <#if h_index % 2 == 0>
                                    <option value="${m}" <#if m==hour.reservationTo!0>selected</#if>><#if h<10>
                                        0</#if>${h}:00
                                    </option>
                                <#else>
                                    <option value="${m+30}" <#if m+30==hour.reservationTo!0>selected</#if>><#if h<10>
                                        0</#if>${h}:30
                                    </option>
                                </#if>
                            </#list>
                        </select>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <div style="padding-top: 30px" class="form-group">
        <div>
            <button id="saveButton" class="btn btn-success ladda-button" data-style="zoom-in" onclick="saveHours()">
            <span class="ladda-label">
                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                Uložiť
            </span>
            </button>
        </div>
    </div>
</div>
<script src="<@spring.url '/js/admin/officeHours.js'/>"></script>
</@pt.dashboardPage>