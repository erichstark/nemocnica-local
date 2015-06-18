<#-- @ftlvariable name="searchTerm" type="java.lang.String" -->
<#-- @ftlvariable name="specializations" type="sk.stuba.fei.team.local.domain.Specialization[]" -->
<#import "../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.menuFooterPage pageTitle="Zmena hesla">

<div class="panel-body">
    <form action="<@spring.url '/employee/password/save'/>" method="post">
        <div class="row">
            <div class="form-group col-md-6 col-xs-6 col-sm-6">
                <label for="employee-prefix_title">Staré heslo</label>
                <input type="password" name="oldPassword" class="form-control" placeholder="Staré heslo" value="">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-6 col-xs-6 col-sm-6">
                <label for="employee-prefix_title">Nové heslo</label>
                <input type="password" name="password" class="form-control" placeholder="Nové heslo" value="">
            </div>
            <div class="form-group col-md-6 col-xs-6 col-sm-6">
                <label for="employee-suffix_title">Heslo znovu</label>
                <input type="password" name="passwordCheck" class="form-control" placeholder="Heslo znovu" value="">
            </div>
        </div>

        <div style="padding-top: 30px" class="form-group">
            <button id="saveButton" class="btn btn-success ladda-button" data-style="zoom-in">
            <span class="ladda-label">
                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                Uložiť
            </span>
            </button>
        </div>
</div>

</@pt.menuFooterPage>